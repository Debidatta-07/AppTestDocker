				                  Agent Testing Application
                                        -------------------------------------------


Application type : Java based Spring Boot Application

Build Tool : Apache Maven

Required JDK version : JDK-8

Application Default Server Port : 8080

	 -> To change Port of the application : use -Dserver.port="port no" in VM arguments in the Run Configuration.

					                                OR

				                use java -jar -Dserver.port="port no" agentTestingApp.war in command line .


List of Supported Services :   ------>
-------------------------------

	HTTP
	DATABASES	
	THREADS
	LDAP
	JMS
	COMPLETABLE FUTURE
	EXCEPTIONS
	REACTIVE
	APACHE SOLR
	REQUEST RESPONSE DUMP
	MEMCACHED
	RESTLET
	DYNAMIC LOGGING
	HAZELCAST
	SOAP
	LOGGER MDC
	Quoted URL


HTTP Services :
---------------

  Apache Commons (org.apache.commons.httpclient.HttpClient)

  Sun (java.net.HttpURLConnection)

  OkHTTP (okhttp3.OkHttpClient)

  Simple Apache HTTP Asynchronous Exchange (org.apache.http.impl.nio.client.HttpAsyncClients)

  Apache HTTP Asynchronous Exchange using Content Exchange (org.apache.http.impl.nio.client.HttpAsyncClients)

  Google Api (com.google.api.client.http)

  Apache Axis (org.apache.axis.transport.http.HTTPSender)

  Apache asynchronous HTTP exchange using a callback interface
  
  Asynchronous Servlet 3.0

	         
DATABASES Services:
-------------------

  IBM Cloudant (lightcouch DB) - 
	* Database : cavisson

  IBM DB2 ( runs on Docker ) -
	* Driver class : com.ibm.db2.jcc.DB2Driver
	* Table by default : people(firstname,lastname)

  SQL Server -
	* Driver Class : com.microsoft.sqlserver.jdbc.SQLServerDriver
	* Table by default : people(firstname,lastname)

  MySql - 
	Ex. Port : 3306
	* Driver Class : com.mysql.cj.jdbc.Driver
	* Table by default : people(firstname,lastname)

  Oracle Database -
	* Driver Class : oracle.jdbc.driver.OracleDriver
	* Table by default : student(firstname,lastname)

  Postgres Database -
	* Driver Class : com.microsoft.sqlserver.jdbc.SQLServerDriver
	* Table by default : people(firstname,lastname) 

  Redis Lettuce - 
	* Ex. Port : 6379
	* Driver Class : com.lambdaworks.redis.RedisClient

  Redis Sync Client -
	* Host : 10.10.40.12 , Port - 6379

  Redis Async Client - 
	* Host : 10.10.40.12 , Port - 6379

  Cassandra DB -

	Version : 3.1.1 & 4.0.1

	* Host : 10.10.40.12 , Port - 9042 
	
  MONGO DB -
  	* Host - 10.10.40.12 , Port - 27017	

 	 List of methods -

		insert , update , deleteFirst , deleteAll ,getCount ,distinct , group , aggregate , mapReducecreateIndex , findandmodify , drop , dropInex , parallelScan , Rename


THREADS Services :
-------------------

  Fork Join Thread

  Simple Thread Pool Executor

  Scheduled Thread Pool Executor Using Runnable Interface

  Scheduled Thread Pool Executor Using Callable Interface

  Scheduled Thread Pool Using Custom Executor Service

  Thread pool

  Thread pool call

  Thread Call Using Executor

  Quartz Job Call


  ReactiveX Java -

     RX java Sync Call

     Rx java Async Call

     Rx java Async on Singe Thread Call

     Rx java Async on Multi Thread Call

  Mutex Lock Using Thread 

  Producer Consumer
 
  Start Multiple Producer Consumer

	     - Runs independently in the application .


LDAP Service :
-----------------

  Required tool for Server -  Apache Directory Studio Server.

  Configure Server before excecution of the methods.

  Create partition in the server.

  Required Configuration in the Server -

	*port
	
	*organisation

	*organisation unit
	
	*inetOrgPerson

  Run the server before execution of the methods.


JMS Services :
----------------

  ActiveMQ - 

	Required tool - Apache ActiveMQ Broker
	
	Host - 10.10.30.134
	
	Port - 61616	

	Tool -> Port - 8161 

	*For ActiveMQ TOPIC RECEIVE method use Apache ActiveMQ Broker-topic to send messages .

  RabbitMQ - 
	
	Required tool - RabbitMQ
	
	Host - 10.10.40.12

  IBMMQ (runs on Docker) - 

	Required tools - Docker , IBM MQ docker image

	Host - 10.10.30.134

	Docker Commands to run IBM MQ image in the host machine :
	---------------------------------------------------------
		* docker pull ibmcom/mq

		* Use Docker to create a volume:

			docker volume create qm1data	
		
		* Create a network that the MQ server container and the MQ client demo container can use to communicate:

			docker network create mq-demo-network

		* Run the MQ server container:
			
			docker run --env LICENSE=accept --env MQ_QMGR_NAME=QM1 --volume qm1data:/mnt/mqm --publish 1414:1414 --publish 9443:9443 --network mq-demo-network --network-alias qmgr --detach --env MQ_APP_PASSWORD=/cavisson/ ibmcom/mq:latest

		* Give the container a moment to start:
			
			docker ps

		* Copy your own container id and use it to get command line access inside the container :
		
			docker exec -ti <your container id> /bin/bash

		* You can display the MQ Installation and Data paths by running the ‘display MQ version’ command in your command line interface:

			dspmqver

		* Display your running queue managers:

			dspmq

		* To come out of the docker container and return to your Linux command line:

			exit

  APACHE KAFKA -

          Host : localhost
	
	  Port : 9092	
	
          * Topic Producer and Consumer
		 

COMPLETABLE FUTURE Service :
-----------------------------

	- Runs independently in the application .


EXCEPTION Services :
-----------------------

  Normal Exception

  Nested Exception

  Normal Exception in Other Packages

  Custom Nested Exception

  NumberFormat Exception

  StringIndex Exception 

  Custom nested Exception

  Defualt - Uncaught Exception
	
	 - Runs independently in the application .


REACTIVE Services:
-------------------

 Redis Service - 

	* Host - 10.10.30.134 
	* PORT - 6379 
	
	* To change host , port : -Dspring.redis.host=host
				  -Dspring.redis.port=port  from command line
		
 Webflux Service - 

	Runs with a n/w connection
	
 Mongo Service - 
	
	* Host - 10.10.40.12 
	* PORT - 27017 
	
	* To change Uri : -Dspring.data.mongodb.uri=mongodb://host:port/db_name  from command line
	
	Tweet Service :

	List of methods - Create Tweet , Get ALL tweets  , Get Tweet by ID

	*For put and delete method use /tweets/{id} from POSTMAN tool.


APACHE SOLR Service :
------------------------

    Default collection name - mycol1

	* Host - 10.10.70.40  , Port - 8983 

	List of methods - 
		
		 Populate 
		 Search

REQUEST RESPONSE DUMP Service :
-------------------------------

    List of methods -

    	GET -> XML response on webpage

    Using Postman Tool -

  	/reqres/saveEmployee -> XML request response
  
    	/reqres/setEmployee -> JSON request response

	/reqres/sendEmployee -> plain text response
		
	   Sample - XML :                                 JSON :
	                                        		 {"id":1,"name":"CAVISSON","age":33,"salary":"5000"}
	           <Employee>
		       <id>1</id>
	               <name>CAVISSON</name>
	               <age>23</age>
	               <salary>5000</salary>
                   </Employee>
				

MEMCACHED Service :
--------------------
	* Host : 10.10.30.134 
	* Port : 11211 

	List of methods -
			get , add , delete , replace

RESTLET Service :
--------------------
	* Host : localhost 
	
	Server
	Client

DYNAMIC LOGGING Service:
-------------------------
	
	Call Method with Lambda expresion , local var ,class field and Return value

	Call Method with Return type Void

	Call Method with Anonymous class and Parent Child Thread
		
	      		- Runs independently in the application.

HAZELCAST Service:
-------------------------
	* Host : localhost 

	Create Instance
	Run client	
			- Runs independently in the application.

SOAP Service:
-------------------------
	* Host : localhost 

	Type 1 : Simple Soap call
	
	Type 2 : Soap MD5 Web Service
	       * Port : 9898 *	
	
			- Runs independently in the application.

Logger MDC Service:
---------------------------
	* slf4j logger 
			- Runs independently in the application.


Quoted URL Service:
----------------------

	Return url address with quotes " "

			- Runs independently in the application.
			

-----------------------------------------------------------------------------------------------------------------------------
