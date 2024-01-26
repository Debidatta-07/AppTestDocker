
package com.cavisson.ata.services;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.TimeoutException;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;

import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import com.ibm.msg.client.jms.JmsConnectionFactory;
import com.ibm.msg.client.jms.JmsFactoryFactory;
import com.ibm.msg.client.wmq.WMQConstants;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

public class JMSService {
	// ACTIVE MQ
	public static String sendActiveMQ(String host, String port, String username, String password, String qname,
			String msg) {

		try {
			String url = "tcp://" + host + ":" + port;
			String subject = qname;
			// Getting JMS connection from the server and starting it
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(username, password, url);
			Connection connection = connectionFactory.createConnection();
			connection.start();

			// Creating a non transactional session to send/receive JMS message.
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			// Destination represents here our queue 'JCG_QUEUE' on the JMS server.
			// The queue will be created automatically on the server.
			Destination destination = session.createQueue(subject);

			// MessageProducer is used for sending messages to the queue.
			MessageProducer producer = session.createProducer(destination);

			// We will send a small text message saying 'Hello World!!!'
			TextMessage message = session.createTextMessage(msg);

			// Here we are sending our message!
			producer.send(message);

			System.out.println("JCG printing@@ '" + message.getText() + "'");
			String s = message.getText();
			connection.close();
			return s;
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "failed";
	}

	public static String receiveAMQ(String host, String port, String username, String password, String qname) {

		// Getting JMS connection from the server
		try {
			String url = "tcp://" + host + ":" + port;
			String subject = qname;
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(username, password, url);
			Connection connection = connectionFactory.createConnection();
			connection.start();

			// Creating session for seding messages
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			// Getting the queue 'JCG_QUEUE'
			Destination destination = session.createQueue(subject);

			// MessageConsumer is used for receiving (consuming) messages
			MessageConsumer consumer = session.createConsumer(destination);

			// Here we receive the message.
			Message message = consumer.receive();

			// We will be using TestMessage in our example. MessageProducer sent us a
			// TextMessage
			// so we must cast to it to get access to its .getText() method.
			if (message instanceof TextMessage) {
				TextMessage textMessage = (TextMessage) message;
				System.out.println("Received message is :" + textMessage.getText() + "'");
			}
			TextMessage textMessage = (TextMessage) message;
			String s = textMessage.getText();

			connection.close();

			return "received msg : " + s;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "failed";
	}

	public static String activeMQTopicPublisher(String host, String port, String username, String password,
			String topic_name, String client_id, String msg) {
		try {

			String url = "tcp://" + host + ":" + port;
			// String tname2 = tname;

			TopicConnectionFactory connectionFactory = new ActiveMQConnectionFactory(username, password, url);
			TopicConnection connection = connectionFactory.createTopicConnection();
			connection.setClientID(client_id);
			connection.start();

			// JMS messages are sent and received using a Session. We will
			// create here a non-transactional session object. If you want
			// to use transactions you should set the first parameter to 'true'
			TopicSession session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
			Topic topic = session.createTopic(topic_name);// session
			TopicPublisher producer = session.createPublisher(topic);

			// We will send a small text message saying 'Hello'

			TextMessage message = session.createTextMessage();

			message.setText(msg);
			// Here we are sending the message!
			producer.send(message);
			System.out.println("Sent message is :" + message.getText() + "'");
			String s = message.getText();
			connection.close();
			return s;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "failed";
	}

	public static String activeMQTopicSubscriber(String host, String port, String username, String password,
			String topic_name, String client_id) {

		String greeting = "no greeting";
		String url = "tcp://" + host + ":" + port;
		try {
			TopicConnectionFactory connectionFactory = new ActiveMQConnectionFactory(username, password, url);
			TopicConnection connection = connectionFactory.createTopicConnection();

			// need to setClientID value, any string value you wish
			connection.setClientID(client_id);
			connection.start();

			TopicSession session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

			Topic topic = session.createTopic(topic_name);

			// need to use createDurableSubscriber() method instead of createConsumer() for
			// topic
			// MessageConsumer consumer = session.createConsumer(topic);
			TopicSubscriber consumer = session.createSubscriber(topic);

			Message message = consumer.receive(30000);

			if (message != null) {
				// cast the message to the correct type
				TextMessage textMessage = (TextMessage) message;

				// retrieve the message content
				String text = textMessage.getText();

				// create greeting
				greeting = "Hello " + text + "!";
				System.out.println(client_id + ": message received - " + greeting);
			} else {
				System.out.println(client_id + ": no message received");
			}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return greeting;
	}

	// RABBIT MQ
	public static String rabitmqSender(String host, String qname, String message, String username, String password) {
		String result = null;
		try {
			com.rabbitmq.client.ConnectionFactory factory = new com.rabbitmq.client.ConnectionFactory();
			factory.setHost(host);
			factory.setUsername(username);
			factory.setPassword(password);
			try (com.rabbitmq.client.Connection connection = factory.newConnection();
					Channel channel = ((com.rabbitmq.client.Connection) connection).createChannel()) {

				channel.queueDeclare(qname, false, false, false, null);
				channel.basicPublish("", qname, null, message.getBytes("UTF-8"));
				result = " Sent '" + message + "'";
				System.out.println("  Sent Message '" + message + "'");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public static String rabitmqReceiever(String host, String qname, String username, String password) {

		try {
			com.rabbitmq.client.ConnectionFactory factory = new com.rabbitmq.client.ConnectionFactory();
			factory.setHost(host);
			factory.setUsername(username);
			factory.setPassword(password);
			com.rabbitmq.client.Connection connection = factory.newConnection();
			Channel channel = connection.createChannel();

			channel.queueDeclare(qname, false, false, false, null);

			Consumer consumer = new DefaultConsumer(channel) {
				@Override
				public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
						byte[] body) throws IOException {
					String message = new String(body, "UTF-8");
					System.out.println(" Received '" + message + "'");
				}
			};
			channel.basicConsume(qname, true, consumer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return " Message Received";
	}

	// IBM MQ
	public static String ibmMQSendMessage(String host, String queue_manager, String username, String password) {

		JMSContext context = null;
		Destination destination = null;
		JMSProducer producer = null;
		String s = null;

		try {
			// Create a connection factory
			JmsFactoryFactory ff = JmsFactoryFactory.getInstance(WMQConstants.WMQ_PROVIDER);
			JmsConnectionFactory cf = ff.createConnectionFactory();

			// Set the properties
			cf.setStringProperty(WMQConstants.WMQ_HOST_NAME, host);
			cf.setIntProperty(WMQConstants.WMQ_PORT, 1414);
			cf.setStringProperty(WMQConstants.WMQ_CHANNEL, "DEV.APP.SVRCONN");
			cf.setIntProperty(WMQConstants.WMQ_CONNECTION_MODE, WMQConstants.WMQ_CM_CLIENT);
			cf.setStringProperty(WMQConstants.WMQ_QUEUE_MANAGER, queue_manager);
			cf.setStringProperty(WMQConstants.WMQ_APPLICATIONNAME, "Sender (JMS)");
			cf.setBooleanProperty(WMQConstants.USER_AUTHENTICATION_MQCSP, true);
			cf.setStringProperty(WMQConstants.USERID, username);
			cf.setStringProperty(WMQConstants.PASSWORD, password);

			// Create JMS objects
			context = cf.createContext();
			destination = context.createQueue("queue:///" + "DEV.QUEUE.1");

			long uniqueNumber = System.currentTimeMillis() % 1000;
			TextMessage message = context.createTextMessage("Your lucky number today is " + uniqueNumber);

			producer = context.createProducer();
			producer.send(destination, message);
			System.out.println("Sent message:\n" + message);

			s = message.getText();

		} catch (JMSException jms) {

		}
		return s;
	}

	public static String ibmMQReceiveMessage(String host, String queue_manager, String username, String password) {
		JMSContext context = null;
		Destination destination = null;
		JMSConsumer consumer = null;

		String s = null;

		try {
			JmsFactoryFactory ff = JmsFactoryFactory.getInstance(WMQConstants.WMQ_PROVIDER);
			JmsConnectionFactory cf = ff.createConnectionFactory();

			// Set the properties
			cf.setStringProperty(WMQConstants.WMQ_HOST_NAME, host);
			cf.setIntProperty(WMQConstants.WMQ_PORT, 1414);
			cf.setStringProperty(WMQConstants.WMQ_CHANNEL, "DEV.APP.SVRCONN");
			cf.setIntProperty(WMQConstants.WMQ_CONNECTION_MODE, WMQConstants.WMQ_CM_CLIENT);
			cf.setStringProperty(WMQConstants.WMQ_QUEUE_MANAGER, queue_manager);
			cf.setStringProperty(WMQConstants.WMQ_APPLICATIONNAME, "Receiver (JMS)");
			cf.setBooleanProperty(WMQConstants.USER_AUTHENTICATION_MQCSP, true);
			cf.setStringProperty(WMQConstants.USERID, username);
			cf.setStringProperty(WMQConstants.PASSWORD, password);

			// Create JMS objects
			context = cf.createContext();
			destination = context.createQueue("queue:///" + "DEV.QUEUE.1");

			consumer = context.createConsumer(destination); // autoclosable
			String receivedMessage = consumer.receiveBody(String.class, 15000); // in ms or 15 seconds

			System.out.println("\nReceived message:\n" + receivedMessage);

			s = receivedMessage;
			return s;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return s;
	}

	// APACHE KAFKA
	public static String kafkaProducer(String host, String port, String topic_name) {
		String topicName = topic_name;
		String kafkaHost = host;

		// create instance for properties to access producer config
		Properties props = new Properties();

		// Assign localhost id
		props.put("bootstrap.servers", kafkaHost + ":" + port);

		// Set acknowledgements for producer requests.
		props.put("acks", "all");

		// If the request fails, the producer can automatically retry,
		props.put("retries", 0);

		// Specify buffer size in config
		props.put("batch.size", 16384);

		// props.put("batch.size", 1);
		// Reduce the no of requests less than 0
		props.put("linger.ms", 0);

		// The buffer.memory controls the total amount of memory available to the
		// producer for buffering.
		props.put("buffer.memory", 33554432);

		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		JMSService in1 = new JMSService();
		System.out.println(" the KafkaProducer class loader is " + in1.getClass().getClassLoader());
		System.out.println("KafkaProducer  parent class loader is " + in1.getClass().getClassLoader().getParent());

		Producer<String, String> producer = new KafkaProducer<String, String>(props);
		ProducerRecord<String, String> prodRecord = new ProducerRecord<String, String>(topicName, "topic----",
				topic_name);

		// ProducerRecord<String, String> prodRecord = new ProducerRecord<String,
		// String>(topicName,Integer.toString(i), "Message is - kafka ProducerReocrd");

		java.util.concurrent.Future<RecordMetadata> metaData = producer.send(prodRecord, new Callback() {
			public void onCompletion(RecordMetadata metadata, Exception e) {

				if (e != null) {
				} else {
					System.out.println("On complition is called");
				}
			}
		});

		try {
			metaData.get();
		}

		catch (Exception ex)

		{

		}

		System.out.println("Data Produced:-Message sent successfully-------------");
		System.out.println(
				"topic name is " + prodRecord.topic() + " produce message is---" + prodRecord.value().toString());
		return prodRecord.value();

		/*
		 * try {
		 * 
		 * Header [] in = prodRecord.headers().toArray(); int i =0;
		 * 
		 * while(i<in.length) { Header obj = in[i]; headreName= new String(obj.key());
		 * headrValue= new String(obj.value());
		 * 
		 * System.out.println("Header is "+headreName +"---->" +headrValue+"\n");
		 * 
		 * i++;
		 * 
		 * 
		 * } }
		 * 
		 * catch(Exception ex) { ex.printStackTrace();
		 * 
		 * }
		 * 
		 * System.out.println("Header print  successfully");
		 * 
		 */

	}

	public void printStackTrace() {
		StringBuilder sb = new StringBuilder();
		StackTraceElement[] stk = Thread.currentThread().getStackTrace();
		for (StackTraceElement stacktrace : stk) {
			sb.append(stacktrace.toString());
			System.out.println("in loop");
			sb.append("\n");
		}

		System.out.println("Stacktrace :" + sb.toString());
	}

	public static String kafkaConsumer(String host, String port, String topic_name) {

		// Kafka consumer configuration settings
		String topicName = topic_name; // topic name
		String kafkaHost = host; // host
		Properties props = new Properties();

		props.put("bootstrap.servers", kafkaHost + ":" + port); // port
		props.put("group.id", "test2");
		props.put("max.poll.records", "1");
		props.put("enable.auto.commit", "true");
		props.put("auto.commit.interval.ms", "1000");
		props.put("session.timeout.ms", "30000");
		props.put("fetch.min.bytes", 1);
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

		// KafkaConsumer <String, String> consumer = null;

		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);

		consumer = new KafkaConsumer<String, String>(props);

		// Kafka Consumer subscribes list of topics here.

		ArrayList<String> topic = new ArrayList<String>();

		topic.add(topicName);

		consumer.subscribe(topic);

		ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(2000));

		System.out.println(" the no of record is -- " + records.count());
		JMSService in = new JMSService();
		if (null != records)
			in.processRecords(records);

		// printStackTrace();
		/*
		 * for (ConsumerRecord<String,String> record : records) {
		 * System.out.println("Now printing headers-"); String headrValue = ""; String
		 * headreName = ""; try {
		 * 
		 * Header [] in = record.headers().toArray(); int i =0;
		 * 
		 * while(i<in.length) { Header obj = in[i]; headreName= new String(obj.key());
		 * headrValue= new String(obj.value());
		 * 
		 * System.out.println("Header is "+headreName +"---->" +headrValue+"\n");
		 * 
		 * i++;
		 * 
		 * 
		 * } }
		 * 
		 * catch(Exception ex) { ex.printStackTrace();
		 * 
		 * }
		 * 
		 * System.out.println("Header print  successfully"); SimpleConsumer in = new
		 * SimpleConsumer();
		 * 
		 * in.processRecord(record);
		 * 
		 * System.out.println("consumer record object is  ---"+record);
		 * System.out.println("topic name is "+record.topic()
		 * +" consume message is---"+record.value().toString()); }
		 */
		consumer.commitSync();
		return topic.toString();
	}

	public void processRecords(ConsumerRecords<String, String> records) {
		if (null != records)
			System.out.println(records.toString());
		else
			System.out.println("There is no record");
	}

	public void processRecord(ConsumerRecord<String, String> record) {
		System.out.println(
				"offset = %d, key = %s, value = %s\n" + record.offset() + "" + record.key() + " " + record.value());
		System.out.println("the consumer message is receiving....");
	}

}
