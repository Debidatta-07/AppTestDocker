package com.cavisson.ata.main;

/**
 * @author Vishal Singh
 *
 */

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.cavisson.ata.models.*;
import com.cavisson.ata.services.*;
import com.cavisson.ata.webservice.soap.WebServiceServer;
import com.cavisson.ata.webservice.soap.client.Client;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

@Controller
public class AppController {

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("/http")
	public String http() {
		return "http";
	}

	@RequestMapping("/database")
	public String database() {
		return "database";
	}

	@RequestMapping("/thread")
	public String thread() {
		return "thread";
	}

	@RequestMapping("/ldap")
	public String ldap() {
		return "ldap";
	}

	@RequestMapping("/jms")
	public String jms() {
		return "jms";
	}

	@GetMapping("/completable")
	public String completable() {
		return "completable";
	}

	@GetMapping("/exception")
	public String exception() {
		return "exception";
	}

	@RequestMapping("/reactive")
	public String reactive() {
		return "reactive";
	}

	@RequestMapping("/solr")
	public String solr() {
		return "solr";
	}

	@RequestMapping("/reqres")
	public String reqres() {
		return "reqres";
	}

	@RequestMapping("/memcached")
	public String memcached() {
		return "memcached";
	}

	@RequestMapping("/restlet")
	public String restlet() {
		return "restlet";
	}

	@RequestMapping("/dynamicLog")
	public String dynamicLog() {
		return "dynamicLog";
	}

	@RequestMapping("/hazelcast")
	public String hazelcast() {
		return "hazelcast";
	}

	@RequestMapping("/soap")
	public String soapcall() {
		return "soap";
	}

	@RequestMapping("/loggermdc")
	public String loggermdc() {
		return "loggermdc";
	}

	@RequestMapping("/" + '"' + "quotes" + '"')
	public String quotes() {
		System.out.println("" + '"' + "url_with_quotes" + '"' + "");
		return "quotes";
	}
	
	@RequestMapping("/resttemplate")
	public String resttamplate() {
		return "resttemplate";
	}

	@GetMapping("/httpcallout")
	public String processHttp(Map<String, Object> request) {
		User model = new User();
		request.put("model", model);
		return "http";
	}

	@PostMapping("/httpcallout")
	public String httpcallout(@ModelAttribute("request") User request, Model model) throws Exception {
		String output = null;
		String client = request.getClient();
		String url = request.getUrl();
		try {
			if (client.equals("apache")) {
				output = HTTPCallService.callApacheHttpClientService(url);
			} else if (client.equals("sun")) {
				output = HTTPCallService.callSunHttpClientService(url);
			} else if (client.equals("okhttp")) {
				output = HTTPCallService.callOkhttpClientService(url);
			} else if (client.equals("asyncHTTP")) {
				output = HTTPCallService.callApacheHttpAsyncForExchange(url);
			} else if (client.equals("asyncHTTPContent")) {
				output = HTTPCallService.callApacheHttpAsyncExchangeForContentStreaming(url);
			} else if (client.equals("googleHTTP")) {
				output = HTTPCallService.callGoogleApi(url);
			} else if (client.equals("axisHTTP")) {
				output = HTTPCallService.callApacheAxisHttpClient(url);
			}
		} catch (Exception e) {
			System.err.println(e);
			output = e.toString();
		}
		model.addAttribute("client", client);
		model.addAttribute("output", output);
		return "callout";
	}

	@GetMapping("/dbcallout")
	public String processDb(Map<String, Object> request) {

		User model = new User();
		request.put("model", model);
		return "database";
	}

	@PostMapping("/dbcallout")
	public String dbcallout(@ModelAttribute("request") User request, Model model) {
		String output = null;
		String client = request.getClient();
		String dbName = request.getDbname();
		String prepared = request.getPrepared();
		String url = request.getUrl();
		String Host = request.getHost();
		String Port = request.getPort();
		String userName = request.getUsername();
		String passWord = request.getPassword();
		String firstName = request.getFirstname();
		String lastName = request.getLastname();
		String server = request.getServerName();
		String database = request.getDatabaseName();
		String collection = request.getCollectionName();
		String newcollection = request.getNewCollectionName();
		String version = request.getVersion();
		String type = request.getType();
		try {
			if (client.equals("cloudant")) {
				System.out.println("dbName = " + dbName);
				String dbname = dbName.split(",")[0];
				output = DatabaseService.cloudantconnect(dbname);
			} else if (client.equals("db2")) {
				String dbname = dbName.split(",")[1];
				String username = userName.split(",")[0];
				String password = passWord.split(",")[0];
				String firstname = firstName.split(",")[0];
				String lastname = lastName.split(",")[0];
				output = DatabaseService.db2connect(url, dbname, username, password, firstname, lastname);
			} else if (client.equals("mssql") && prepared.equals("yes")) {
				String dbname = dbName.split(",")[2];
				String host = Host.split(",")[0];
				String port = Port.split(",")[0];
				String username = userName.split(",")[1];
				String password = passWord.split(",")[1];
				String firstname = firstName.split(",")[1];
				String lastname = lastName.split(",")[1];
				output = DatabaseService.mssqlPreparedConnect(host, username, password, dbname, port, firstname,
						lastname);
			} else if (client.equals("mssql") && prepared.equals("no")) {
				String dbname = dbName.split(",")[2];
				String host = Host.split(",")[0];
				String port = Port.split(",")[0];
				String username = userName.split(",")[1];
				String password = passWord.split(",")[1];
				output = DatabaseService.mssqlNonPrepConnect(host, username, password, dbname, port);
			} else if (client.equals("mysql") && prepared.equals("yes")) {
				String dbname = dbName.split(",")[3];
				String host = Host.split(",")[1];
				String username = userName.split(",")[2];
				String password = passWord.split(",")[2];
				String firstname = firstName.split(",")[2];
				String lastname = lastName.split(",")[2];
				System.out.println(host);
				output = DatabaseService.mysqlPreparedConnect(dbname, username, password, firstname, lastname, host);
			} else if (client.equals("mysql") && prepared.equals("no")) {
				String dbname = dbName.split(",")[3];
				String host = Host.split(",")[1];
				String username = userName.split(",")[2];
				String password = passWord.split(",")[2];
				output = DatabaseService.mysqlNonPrepConnect(dbname, username, password, host);
			} else if (client.equals("oracle") && prepared.equals("yes")) {
				String host = Host.split(",")[2];
				String port = Port.split(",")[1];
				String username = userName.split(",")[3];
				String password = passWord.split(",")[3];
				String firstname = firstName.split(",")[3];
				String lastname = lastName.split(",")[3];
				String Type = type.split(",")[0];
				System.out.println(Type);
				output = DatabaseService.oraclePreparedConnect(host, port, username, password, firstname, lastname,
						Type);
			} else if (client.equals("oracle") && prepared.equals("no")) {
				String host = Host.split(",")[2];
				String port = Port.split(",")[1];
				String username = userName.split(",")[3];
				String password = passWord.split(",")[3];
				String Type = type.split(",")[0];
				System.out.println(Type);
				output = DatabaseService.oracleNonPrepConnect(host, port, username, password, Type);
			}

			else if (client.equals("postgres") && prepared.equals("yes")) {
				String host = Host.split(",")[3];
				String dbname = dbName.split(",")[4];
				String username = userName.split(",")[4];
				String password = passWord.split(",")[4];
				String firstname = firstName.split(",")[3];
				String lastname = lastName.split(",")[3];
				System.out.println(host);
				output = DatabaseService.postgresPreparedConnect(host, dbname, username, password, firstname, lastname);
			} else if (client.equals("postgres") && prepared.equals("no")) {
				String host = Host.split(",")[3];
				String dbname = dbName.split(",")[4];
				String username = userName.split(",")[4];
				String password = passWord.split(",")[4];
				String firstname = firstName.split(",")[3];
				String lastname = lastName.split(",")[3];
				System.out.println(host);
				output = DatabaseService.postgresNonPrepConnect(host, dbname, username, password, firstname, lastname);
			}

			else if (client.equals("lettuce")) {
				String host = Host.split(",")[4];
				output = DatabaseService.redisCall(host);
			} else if (client.equals("redissyncclient")) {
				String host = Host.split(",")[5];
				String port = Port.split(",")[2];
				output = DatabaseService.RedisSyncClient(host, port);
			}

			else if (client.equals("redisasyncclient")) {
				String host = Host.split(",")[6];
				String port = Port.split(",")[3];
				output = DatabaseService.RedisAsyncClient(host, port);
			}

			else if (client.equals("cassandraclient") && version.equals("three")) {

				String host = Host.split(",")[7];
				System.out.println(host);
				output = DatabaseService.cassandradb3(host);
			} else if (client.equals("cassandraclient") && version.equals("four")) {

				String host = Host.split(",")[7];
				System.out.println(host);
				output = DatabaseService.cassandradb4(host);
			}
			// MONGO DB
			else if (client.equals("insert")) {
				output = DatabaseService.Insert(server, database, collection);
			} else if (client.equals("update")) {
				output = DatabaseService.Update(server, database, collection);
			} else if (client.equals("deleteFirst")) {
				output = DatabaseService.DeleteFirst(server, database, collection);
			} else if (client.equals("deleteAll")) {
				output = DatabaseService.DeleteAll(server, database, collection);
			} else if (client.equals("getCount")) {
				output = DatabaseService.GetCount(server, database, collection);
			} else if (client.equals("rename")) {
				output = DatabaseService.Rename(server, database, collection, newcollection);
			} else if (client.equals("distinct")) {
				output = DatabaseService.Distinct(server, database, collection);
			} else if (client.equals("group")) {
				output = DatabaseService.Group(server, database, collection);
			} else if (client.equals("aggregate")) {
				output = DatabaseService.Aggregate(server, database, collection);
			} else if (client.equals("mapreduce")) {
				output = DatabaseService.MapReduce(server, database, collection);
			} else if (client.equals("createindex")) {
				output = DatabaseService.CreateIndex(server, database, collection);
			} else if (client.equals("findandmodify")) {
				output = DatabaseService.FindAndModify(server, database, collection);
			} else if (client.equals("drop")) {
				output = DatabaseService.Drop(server, database, collection);
			} else if (client.equals("dropindex")) {
				output = DatabaseService.DropIndex(server, database, collection);
			} else if (client.equals("parallelscan")) {
				output = DatabaseService.ParallelScan(server, database, collection);
			}
		} catch (Exception e) {
			System.err.println(e);
			output = e.toString();
		}
		model.addAttribute("client", client);
		model.addAttribute("output", output);
		return "callout";
	}

	@GetMapping("/threadcallout")
	public String processThread(Map<String, Object> request) {

		User model = new User();
		request.put("model", model);
		return "thread";
	}

	@PostMapping("/threadcallout")
	public String threadcallout(@ModelAttribute("request") User request, Model model) {
		String output = null;
		String client = request.getClient();
		try {
			if (client.equals("fork")) {
				output = ThreadCall.callForkJoinThreadService();
			} else if (client.equals("simple")) {
				output = ThreadCall.callSimpleThreadPoolExecutorService();
			} else if (client.equals("scheduledRunnable")) {
				output = ThreadCall.callScheduledThreadPoolExecutorRunnableService();
			} else if (client.equals("scheduledCallable")) {
				output = ThreadCall.callScheduledThreadPoolExecutorCallableService();
			} else if (client.equals("scheduledExecuter")) {
				output = ThreadCall.myExceuter();
			} else if (client.equals("makeThreadPool")) {
				output = ThreadCall.createThreadPool();
			} else if (client.equals("makeThreadPoolCall")) {
				output = ThreadCall.testThreadPool();
			} else if (client.equals("makeThreadPoolUsingExecutor")) {
				output = ThreadCall.makeThreadPoolUsingExecutor();
			} else if (client.equals("makeQuartzCall")) {
				output = ThreadCall.executeMyJob();
			} else if (client.equals("RxjavaSyncCall")) {
				output = ThreadCall.syncCall();
			} else if (client.equals("RxjavaAsyncCall")) {
				output = ThreadCall.asyncCall();
			} else if (client.equals("RxjavaAsyncTheadCall")) {
				output = ThreadCall.asyncCallOnSingle();
			} else if (client.equals("RxjavaAsyncMultiTheadCall")) {
				output = ThreadCall.asyncCallMultipleThreads();
			} else if (client.equals("mutexlockCall")) {
				output = ThreadCall.mutexthread();
			} else if (client.equals("ProdConsCall")) {
				output = ThreadCall.prodConExample();
			} else if (client.equals("MultiProdConCall")) {
				output = ThreadCall.multiProdCon();
			}
		} catch (Exception e) {
			System.err.println(e);
			output = e.toString();
		}
		model.addAttribute("client", client);
		model.addAttribute("output", output);
		return "callout";
	}

	@GetMapping("/ldapcallout")
	public String processLdap(Map<String, Object> request) {

		User model = new User();
		request.put("model", model);
		return "ldap";
	}

	@PostMapping("/ldapcallout")
	public String ldapcallout(@ModelAttribute("request") User request, Model model) {

		String output = null;
		String client = request.getClient();
		String Employee_number = request.getEmpno();
		String Firstname = request.getFirstname();
		String Lastname = request.getLastname();
		String Organisation = request.getOrg();
		String Organisation_unit = request.getOrgunit();
		String Port = request.getPort();
		String Host = request.getHost();
		String Newpath = request.getNewpath();

		try {

			if (client.equals("add")) {
				String employee_number = Employee_number.split(",")[0];
				String firstname = Firstname.split(",")[0];
				String lastname = Lastname.split(",")[0];
				String organisation = Organisation.split(",")[0];
				String organisation_unit = Organisation_unit.split(",")[0];
				String port = Port.split(",")[0];
				String host = Host.split(",")[0];
				output = LDAPService.add(employee_number, firstname, lastname, host, port, organisation,
						organisation_unit);
			} else if (client.equals("fetch")) {
				String employee_number = null;
				String firstname = null;
				String lastname = null;
				String organisation = Organisation.split(",")[1];
				String organisation_unit = Organisation_unit.split(",")[1];
				String port = Port.split(",")[1];
				String host = Host.split(",")[1];

				output = LDAPService.fetch(employee_number, firstname, lastname, host, port, organisation,
						organisation_unit);
			} else if (client.equals("delete")) {
				String employee_number = Employee_number.split(",")[1];
				String firstname = null;
				String lastname = null;
				String organisation = Organisation.split(",")[2];
				String organisation_unit = Organisation_unit.split(",")[2];
				String port = Port.split(",")[2];
				String host = Host.split(",")[2];
				output = LDAPService.delete(employee_number, firstname, lastname, host, port, organisation,
						organisation_unit);
			} else if (client.equals("modify")) {
				String employee_number = Employee_number.split(",")[2];
				String firstname = Firstname.split(",")[1];
				String lastname = Lastname.split(",")[1];
				String organisation = Organisation.split(",")[3];
				String organisation_unit = Organisation_unit.split(",")[3];
				String port = Port.split(",")[3];
				String host = Host.split(",")[3];
				output = LDAPService.modify(employee_number, firstname, lastname, host, port, organisation,
						organisation_unit);
			} else if (client.equals("moddn")) {
				String employee_number = Employee_number.split(",")[3];
				String firstname = null;
				// String lastname = Lastname.split(",")[1];
				String organisation = Organisation.split(",")[4];
				String organisation_unit = Organisation_unit.split(",")[4];
				String port = Port.split(",")[4];
				String host = Host.split(",")[4];
				String newpath = Newpath.split(",")[0];
				output = LDAPService.moddn(employee_number, firstname, host, port, organisation, organisation_unit,
						newpath);
			} else if (client.equals("compare")) {
				String employee_number = Employee_number.split(",")[4];
				String firstname = null;
				// String lastname = Lastname.split(",")[1];
				String organisation = Organisation.split(",")[5];
				String organisation_unit = Organisation_unit.split(",")[5];
				String port = Port.split(",")[5];
				String host = Host.split(",")[5];
				output = LDAPService.compare(employee_number, firstname, host, port, organisation, organisation_unit);
			} else if (client.equals("extendedStartTls")) {

				String port = Port.split(",")[6];
				String host = Host.split(",")[6];
				output = LDAPService.extendedStartTls(host, port);
			}

		} catch (Exception e) {
			System.err.println(e);
			output = e.toString();
		}

		model.addAttribute("client", client);
		model.addAttribute("output", output);
		return "callout";
	}

	@GetMapping("/jmscallout")
	public String processJms(Map<String, Object> request) {

		User model = new User();
		request.put("model", model);
		return "jms";
	}

	@PostMapping("/jmscallout")
	public String jmscallout(@ModelAttribute("request") User request, Model model) {
		String output = null;
		String client = request.getClient();
		String Host = request.getHost();
		String Port = request.getPort();
		String Qname = request.getQname();
		String Username = request.getUsername();
		String Password = request.getPassword();
		String Topic_Name = request.getTopicname();
		String Client_Id = request.getClientid();
		String Message = request.getMessage();
		String Qmanager = request.getQmanager();

		try {

			if (client.equals("sendAMQ")) {
				String port = Port.split(",")[0];
				String host = Host.split(",")[0];
				String qname = Qname.split(",")[0];
				String message = Message.split(",")[0];
				String username = Username.split(",")[0];
				String password = Password.split(",")[0];
				output = JMSService.sendActiveMQ(host, port, username, password, qname, message);

			} else if (client.equals("receiveAMQ")) {
				String port = Port.split(",")[1];
				String host = Host.split(",")[1];
				String qname = Qname.split(",")[1];
				String username = Username.split(",")[1];
				String password = Password.split(",")[1];
				output = JMSService.receiveAMQ(host, port, username, password, qname);
			}

			else if (client.equals("topicpublisher")) {
				String port = Port.split(",")[2];
				String host = Host.split(",")[2];
				String topic_name = Topic_Name.split(",")[0];
				String client_id = Client_Id.split(",")[0];
				String message = Message.split(",")[1];
				String username = Username.split(",")[2];
				String password = Password.split(",")[2];
				output = JMSService.activeMQTopicPublisher(host, port, username, password, topic_name, client_id,
						message);
			}

			else if (client.equals("topicsubscriber")) {
				String port = Port.split(",")[3];
				String host = Host.split(",")[3];
				String topic_name = Topic_Name.split(",")[1];
				String client_id = Client_Id.split(",")[1];
				String username = Username.split(",")[3];
				String password = Password.split(",")[3];
				output = JMSService.activeMQTopicSubscriber(host, port, username, password, topic_name, client_id);
			}

			else if (client.equals("rabbitmqsender")) {
				String host = Host.split(",")[4];
				String qname = Qname.split(",")[2];
				String message = Message.split(",")[2];
				String username = Username.split(",")[4];
				String password = Password.split(",")[4];

				System.out.println(host);
				System.out.println(qname);
				System.out.println(message);
				System.out.println(username);
				System.out.println(password);
				output = JMSService.rabitmqSender(host, qname, message, username, password);
			}

			else if (client.equals("rabbitmqreceiver")) {
				String host = Host.split(",")[5];
				String qname = Qname.split(",")[3];
				String username = Username.split(",")[5];
				String password = Password.split(",")[5];
				output = JMSService.rabitmqReceiever(host, qname, username, password);
			}

			else if (client.equals("ibmmqsender")) {
				String host = Host.split(",")[6];
				String qmanager = Qmanager.split(",")[0];
				String username = "";
				String password = "";
				System.out.println(qmanager);
				output = JMSService.ibmMQSendMessage(host, qmanager, username, password);
			}

			else if (client.equals("ibmmqreceiver")) {
				String host = Host.split(",")[7];
				String qmanager = Qmanager.split(",")[1];
				String username = "";
				String password = "";
				System.out.println(qmanager);
				output = JMSService.ibmMQReceiveMessage(host, qmanager, username, password);
			}

			else if (client.equals("kafkaproducer")) {
				String host = Host.split(",")[8];
				String port = Port.split(",")[4];
				String topic_name = Topic_Name.split(",")[2];
				System.out.println(host + port + topic_name);
				output = JMSService.kafkaProducer(host, port, topic_name);
			}

			else if (client.equals("kafkaconsumer")) {
				String host = Host.split(",")[9];
				String port = Port.split(",")[5];
				String topic_name = Topic_Name.split(",")[3];
				System.out.println(host + port + topic_name);
				output = JMSService.kafkaConsumer(host, port, topic_name);
			}

		} catch (Exception e) {
			System.err.println(e);
			output = e.toString();
			e.printStackTrace();
		}

		model.addAttribute("client", client);
		model.addAttribute("output", output);
		return "callout";
	}

	@GetMapping("/completablecall")
	public String processCompletableFuture(Map<String, Object> request) {

		User model = new User();
		request.put("model", model);
		return "completableFuture";
	}

	@PostMapping("/completablecall")
	public String completablecall(@ModelAttribute("request") Order order, Model model) {
		String output = null;
		String client = "CFasync";
		int orderId = order.getOrderId();
		String orderAdd = order.getOrderAdd();
		int orderItemQty = order.getOrderItemQty();
		try {
			output = CompletableService.callAsync(orderId, orderAdd, orderItemQty);
		} catch (Exception e) {
			System.err.println(e);
			output = e.toString();
		}
		model.addAttribute("client", client);
		model.addAttribute("output", output);
		return "callout";
	}

	@GetMapping("/exceptioncall")
	public String processException(Map<String, Object> request) {

		User model = new User();
		request.put("model", model);
		return "exception";
	}

	@PostMapping("/exceptioncall")
	public String exceptioncall(@ModelAttribute("request") User request, Model model)throws Exception {
		String output = null;
		String client = request.getClient();
		int choice = request.getChoice();
		
			if (client.equals("normalexception")) {
				output = ExceptionService.normalException();
			} else if (client.equals("nestedexception")) {
				output = ExceptionService.nestedException();
			} else if (client.equals("nrmlexceptionoth")) {
				System.out.println("tryCatch called");
				
					int i = 5 / 0;
					System.out.println("tryCatch() called");
					
				output = "normal exception in diff packages executed";
			} else if (client.equals("uncaughtexcep")) {
				output = ExceptionService.defUncaugtExcep(choice);
			} else if (client.equals("customException")) {
				output = ExceptionService.customException();
			} else if (client.equals("numberformatException")) {
				output = ExceptionService.numberform();
			} else if (client.equals("stringIndxException")) {
				output = ExceptionService.Stringindx();
			}else if (client.equals("nestedCustomException")) {
			  ExceptionService excp = new ExceptionService();
        output = excp.method7();
      }
		
		model.addAttribute("client", client);
		model.addAttribute("output", output);
		return "callout";
	}

	@GetMapping("/webfluxHTTPCallout")
	public String processWebflux(Map<String, Object> request) {

		User model = new User();
		request.put("model", model);
		return "webflux";
	}

	@GetMapping("/solrcallout")
	public String processsolr(Map<String, Object> request) {

		User model = new User();
		request.put("model", model);
		return "solr";
	}

	@PostMapping("/solrcallout")
	public String solr(@ModelAttribute("request") User request, Model model) {
		String output = null;
		String client = request.getClient();
		String Host = request.getHost();
		String Port = request.getPort();
		String Collection = request.getCollection();

		try {
			if (client.equals("populate")) {
				output = SolrJService.populate(Host, Port, Collection);
			}
			if (client.equals("search")) {
				output = SolrJService.searcher(Host, Port, Collection);
			}

		} catch (Exception e) {
			System.err.println(e);
			output = e.toString();
		}

		model.addAttribute("client", client);
		model.addAttribute("output", output);
		return "callout";

	}

	@GetMapping("/memcachedcallout")
	public String processMemcached(Map<String, Object> request) {
		User model = new User();
		request.put("model", model);
		return "memcached";
	}

	@PostMapping("/memcachedcallout")
	public String memcachedcallout(@ModelAttribute("request") User request, Model model) {
		String output = null;
		String client = request.getClient();
		String Host = request.getHost();
		try {
			if (client.equals("Memcached")) {
				output = MemcacheServices.implementXMemCachedClient(Host);
			}
		} catch (Exception e) {
			System.err.println(e);
			output = e.toString();
		}
		model.addAttribute("client", client);
		model.addAttribute("output", output);
		return "callout";

	}

	@GetMapping("/restletcallout")
	public String processRestlet(Map<String, Object> request) {
		User model = new User();
		request.put("model", model);
		return "restlet";
	}

	@PostMapping("/restletcallout")
	public String restletcallout(@ModelAttribute("request") User request, Model model) throws Exception {
		String output = null;
		String client = request.getClient();
		String Port = request.getPort();

		try {
			if (client.equals("resletServer")) {
				output = ResletServices.reserver(Port);

			} else if (client.equals("resletClient")) {
				output = ResletServices.resclient(Port);

			}
		} catch (Exception e) {
			System.err.println(e);
			output = e.toString();
		}
		model.addAttribute("client", client);
		model.addAttribute("output", output);
		return "callout";

	}

	@GetMapping("/dlcallout")
	public String processdl(Map<String, Object> request) {
		User model = new User();
		request.put("model", model);
		return "dynamicLog";
	}

	@PostMapping("/dlcallout")
	public String dlcallout(@ModelAttribute("request") User request, Model model) {
		String output = null;
		String client = request.getClient();
		try {
			if (client.equals("LambdaReturn")) {
				output = DLServices.lambdaReturn();
			} else if (client.equals("voidmethod")) {
				output = DLServices.mvoid();
			} else if (client.equals("anonymousPCthread")) {
				output = DLServices.anonymousPCthread();
			}
		} catch (Exception e) {
			System.err.println(e);
			output = e.toString();
		}
		model.addAttribute("client", client);
		model.addAttribute("output", output);
		return "callout";
	}

	@GetMapping("/hazelcastcallout")
	public String processhazelcast(Map<String, Object> request) {
		User model = new User();
		request.put("model", model);
		return "hazelcast";
	}

	@PostMapping("/hazelcastcallout")
	public String hazelcastcallout(@ModelAttribute("request") User request, Model model) {
		String output = null;
		String client = request.getClient();
		try {
			if (client.equals("hazelinstance")) {
				output = HazelCastServices.hazelInstance();
			} else if (client.equals("hazelclient")) {
				output = HazelCastServices.hazelClient();
			}
		} catch (Exception e) {
			System.err.println(e);
			output = e.toString();
		}

		model.addAttribute("client", client);
		model.addAttribute("output", output);
		return "callout";
	}

	@GetMapping("/soapcallout")
	public String soapcallout(Map<String, Object> request) {

		User model = new User();
		request.put("model", model);
		return "soap";
	}

	@PostMapping("/soapcallout")
	public String soapcallout(@ModelAttribute("request") User request, Model model) {
		String output = null;
		String client = request.getClient();
		try {
			if (client.equals("soapCall")) {
				output = SoapService.soapCall();
			} else if (client.equals("soapserver")) {
				output = WebServiceServer.Response();

			} else if (client.equals("soapclient")) {
				output = Client.Request();
			}
		} catch (Exception e) {
			System.err.println(e);
			output = e.toString();
		}
		model.addAttribute("client", client);
		model.addAttribute("output", output);
		return "callout";
	}

	@GetMapping("/loggerMDCcallout")
	public String processLoggerMDC(Map<String, Object> request) {
		User model = new User();
		request.put("model", model);
		return "loggermdc";
	}

	@PostMapping("/loggerMDCcallout")
	public String loggerMDCcallout(@ModelAttribute("request") User request, Model model) {
		String output = null;
		String client = request.getClient();
		try {
			if (client.equals("example1")) {
				output = LoggerMDCServices.tt();
			} else if (client.equals("example2")) {
				output = LoggerMDCServices.rx();
			}
		} catch (Exception e) {
			System.err.println(e);
			output = e.toString();
		}
		model.addAttribute("client", client);
		model.addAttribute("output", output);
		return "callout";
	}
	
	@GetMapping("/resttemplatecallout")
	public String resttamplate(Map<String, Object> request) {
		User model = new User();
		request.put("model", model);
		return "resttamplate";
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	@Autowired
	RestTemplate restTemplate;
	
	/* @author Gaurav Haldiya */
	@PostMapping("/resttemplatecallout")
	public String sendGetResponse(@ModelAttribute("request") User request, Model model) {
		try {
			ResponseEntity<String> responseEntity = restTemplate.exchange(request.getUrl(), HttpMethod.GET, null,
					String.class);
			if (responseEntity.getStatusCode().is2xxSuccessful()) {
				responseEntity.ok(responseEntity.getBody());
				System.out.println("Resttamplate call is done");
				model.addAttribute("output", "RestTemplate callout is done");
				return "callout";
			}
		} catch (Exception e) {
			System.out.println("URL is wrong or Some Internal Server Error");
			model.addAttribute("output", "URL is wrong or Some Internal Server Error");
			return "error-500";
		}
		return null;
	}

}
