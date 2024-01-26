package com.cavisson.ata.main;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cavisson.ata.models.Employee;
import com.cavisson.ata.models.Tweet;
import com.cavisson.ata.models.User;
import com.cavisson.ata.services.RedisService;
import com.cavisson.ata.services.SynonymsService;
import com.cavisson.ata.utils.ErrorResponse;
import com.cavisson.ata.utils.LoggerMDCWork;
import com.cavisson.ata.utils.TweetNotFoundException;
import com.cavisson.ata.utils.TweetRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class AppRestController
{

  @Autowired
  private SynonymsService synonymsService;

  @Autowired
  public RedisService userRedisService;

  @Autowired
  public TweetRepository tweetRepository;

  // REACTIVE REDIS
  @PostMapping(value = "/reactive/{key}", consumes = "application/x-www-form-urlencoded")
  public Mono<Boolean> put(@PathVariable("key") String key, User user)
  {
    return userRedisService.put(key, user);
  }

  @GetMapping("/reactive/{key}")
  public Mono<User> get(@PathVariable("key") String key)
  {
    return userRedisService.get(key);
  }

  @DeleteMapping("/reactive/{key}")
  public Mono<Boolean> delete(@PathVariable("key") String key)
  {

    return userRedisService.delete(key);
  }

  @PostMapping("/webfluxHTTPCallout")
  public Mono<String> wordSynonym(@RequestParam String word)
  {
    System.out.println("weblux service called");
    return synonymsService.getSynonym(word);
  }

  // REACTIVE MONGO
  @GetMapping("/tweets")
  public Flux<Tweet> getAllTweets()
  {
    return tweetRepository.findAll();
  }

  @PostMapping(value = "/tweets", consumes = "application/x-www-form-urlencoded")
  public Mono<Tweet> createTweetsUrl(@Valid Tweet tweet)
  {
    return tweetRepository.save(tweet);
  }

  @PostMapping(value = "/tweets")
  public Mono<Tweet> createTweets(@Valid Tweet tweet)
  {
    return tweetRepository.save(tweet);
  }

  @GetMapping(value = "/tweetsbyid")
  public Mono<ResponseEntity<Tweet>> getTweetById(Tweet tweet)
  {
    String tweetId = tweet.getId();
    System.out.println(tweetId);
    return tweetRepository.findById(tweetId).map(savedTweet -> ResponseEntity.ok(savedTweet)).defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @PutMapping("/tweets/{id}")
  public Mono<ResponseEntity<Tweet>> updateTweet(@PathVariable(value = "id") String tweetId,

      @Valid @RequestBody Tweet tweet)
  {
    System.out.println("id");
    return tweetRepository.findById(tweetId).flatMap(existingTweet -> {
      existingTweet.setText(tweet.getText());
      return tweetRepository.save(existingTweet);
    }).map(updateTweet -> new ResponseEntity<>(updateTweet, HttpStatus.OK)).defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @DeleteMapping("/tweets/{id}")
  public Mono<ResponseEntity<Void>> deleteTweet(@PathVariable(value = "id") String tweetId)
  {
    return tweetRepository.findById(tweetId).flatMap(existingTweet -> tweetRepository.delete(existingTweet).then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))).defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  // Tweets are Sent to the client as Server Sent Events
  @GetMapping(value = "/stream/tweets", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<Tweet> streamAllTweets()
  {
    return tweetRepository.findAll();
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleDuplicateKeyException(Exception ex)
  {
    return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse("Error Occured"));
  }

  @ExceptionHandler(TweetNotFoundException.class)
  public ResponseEntity<?> handleTweetNotFoundException(TweetNotFoundException ex)
  {
    return ResponseEntity.notFound().build();
  }

  // REQUEST RESPONSE DUMP XML AND JSON
  @RequestMapping(value = "/reqres/getEmployee", method = RequestMethod.GET)
  public Employee getEmployeeInfo()
  {
    Employee employee = new Employee();
    employee.setId(2);
    employee.setName("cavisson");
    employee.setAge(43);
    employee.setSalary(10000);
    System.out.println("request executed");
    System.out.println(employee);
    return employee;

  }

  @PostMapping(value = "/reqres/saveEmployee", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
  public String saveEmployeeInformation(@RequestBody Employee employee)
  {

    System.out.println("XML format employee saved successfully---" + employee);
    return "XML format employee saved successfully---->" + employee;
  }

  @PostMapping(value = "/reqres/setEmployee", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public String setEmployeeInformation(@RequestBody Employee employee)
  {

    System.out.println("JSON format employee saved successfully---" + employee);
    return "JSON format employee saved successfully---->" + employee;
  }

  @RequestMapping(value = "/reqres/sendEmployee", method = RequestMethod.POST)
  public String saveEmployeeInfo(@RequestBody Employee employee)
  {

    System.out.println("plain text format employee saved successfully---" + employee);
    return "plain text format employee saved successfully---->" + employee;
  }

  private final Logger log = LoggerFactory.getLogger(this.getClass());

  @RequestMapping("/logger")
  public String tt()
  {
    log.debug("debug level log");

    log.info("info level log");

    log.error("error level log");

    log.info("another info log with {}, {} and {} arguments", 1, "2", 3.0);

    log.trace(" trace level log");

    log.warn(" warning level log");

    LoggerMDCWork t = new LoggerMDCWork();
    t.dynamic();

    return "loggermdc";
  }

}