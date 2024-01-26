<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!-- Static content -->
<link rel="stylesheet" href="static/css/style.css">
<script type="text/javascript" src="static/js/app.js"></script>
<title>Spring Boot : JMS</title>
</head>
<body>
	<ul>
		<li><a href="/">Home</a></li>
		<li><a href="http">HTTP</a></li>
		<li><a href="database">DATABASES</a></li>
		<li><a href="thread">THREADS</a></li>
		<li><a href="ldap">LDAP</a></li>
		<li><a class="active" href="jms">JMS</a></li>
		<li><a href="completable">COMPLETABLE FUTURE</a></li>
		<li><a href="exception">EXCEPTIONS</a></li>
		<li><a href="reactive">REACTIVE</a></li>
		<li><a href="solr">APACHE SOLR</a></li>
		<li><a href="reqres">REQUEST RESPONSE DUMP</a></li>
		<li><a href="memcached">MEMCACHED</a></li>
		<li><a href="restlet">RESTLET</a></li>
		<li><a href="dynamicLog">DYNAMIC LOGGING</a></li>
		<li><a href="hazelcast">HAZELCAST</a></li>
		<li><a href="soap">SOAP</a></li>
		<li><a href="loggermdc">LOGGER MDC</a></li>
		<li><a href='"quotes"'>QUOTED URL</a></li>
		<li><a  href="resttemplate">RESTTEMPLATE</a></li>
	</ul>

	<div style="margin-left: 25%; padding: 1px 16px; height: 500px;">
		<form class="myForm" method="post" action="jmscallout">

			<fieldset>
				<legend>JMS Services</legend>
				<p>
					<label class="choice"> <input type="radio" name="client"
						value="sendAMQ" checked="checked" onchange="sendAMQ()" />
						ActiveMQ QUEUE SEND

					</label>
				</p>

				<p>
					<label class="choice"> <input type="radio" name="client"
						value="receiveAMQ" onchange="receiveAMQ()" /> ActiveMQ QUEUE
						RECEIVE
					</label>
				</p>

				<p>
					<label class="choice"> <input type="radio" name="client"
						value="topicpublisher" onchange="topicpublisher()" /> ActiveMQ
						TOPIC SEND

					</label>
				</p>
				<p>
					<label class="choice"> <input type="radio" name="client"
						value="topicsubscriber" onchange="topicsubscriber()" /> ActiveMQ
						TOPIC RECEIVE
					</label>
				</p>
				<p>
					<label class="choice"> <input type="radio" name="client"
						value="rabbitmqsender" onchange="rabbitmqsender()" /> RabbitMQ
						SENDER
					</label>
				</p>
				<p>
					<label class="choice"> <input type="radio" name="client"
						value="rabbitmqreceiver" onchange="rabbitmqreceiver()" /> RabbitMQ RECEIVER
					</label>
				</p>
				<p>
					<label class="choice"> <input type="radio" name="client"
						value="ibmmqsender" onchange="ibmmqsender()" /> IBMMQ SENDER
					</label>
				</p>
				<p>
					<label class="choice"> <input type="radio" name="client"
						value="ibmmqreceiver" onchange="ibmmqreceiver()" /> IBMMQ
						RECEIVER
					</label>
				</p>


				<p>
					<label class="choice"> <input type="radio" name="client"
						value="kafkaproducer" onchange="kafkaproducer()" /> KAFKA
						PRODUCER
					</label>
				</p>

				<p>
					<label class="choice"> <input type="radio" name="client"
						value="kafkaconsumer" onchange="kafkaconsumer()" /> KAFKA
						CONSUMER
					</label>
				</p>
				
			</fieldset>

			<div id="sendAMQ">
				<p>***All the fields are required***</p>
				<p>
					<label>host :<input type="text" name="host"
						value="10.10.30.134">
					</label> <label>port :<input type="text" name="port" value="61616">
					</label>
				</p>
				<p>
					<label>queue name :<input type="text" name="qname"
						value="JCG_QUEUE">
					</label> <label>message :<input type="text" name="message"
						value="hello ActiveAMQ">
					</label>
				</p>

				<p>
					<label>username :<input type="text" name="username"
						value="admin">
					</label> <label>password :<input type="text" name="password"
						value="admin">
					</label>
				</p>

			</div>
			<div id="receiveAMQ" style="display: none;">
				<p>***All the fields are required***</p>
				<p>
					<label>host :<input type="text" name="host"
						value="10.10.30.134">
					</label> <label>port :<input type="text" name="port" value="61616">
					</label>
				</p>

				<p>
					<label>queue name :<input type="text" name="qname"
						value="JCG_QUEUE">
					</label>
				</p>
				<p>
					<label>username :<input type="text" name="username"
						value="admin">
					</label> <label>password :<input type="text" name="password"
						value="admin">
					</label>
				</p>

			</div>


			<div id="topicpublisher" style="display: none;">
				<p>***All the fields are required***</p>
				<p>
					<label>host :<input type="text" name="host"
						value="10.10.30.134">
					</label> <label>port :<input type="text" name="port" value="61616">
					</label>
				</p>

				<p>
					<label>topic name :<input type="text" name="topicname"
						value="test">
					</label> <label>client id :<input type="text" name="clientid"
						value="12345">
					</label>
				</p>
				<p>

					<label>message :<input type="text" name="message"
						value="hi">
					</label>
				</p>

				<p>
					<label>username :<input type="text" name="username"
						value="admin">
					</label> <label>password :<input type="text" name="password"
						value="admin">
					</label>
				</p>

			</div>

			<div id="topicsubscriber" style="display: none;">
				<p>***All the fields are required***</p>
				<p>***send message using ActiveMQ services***</p>
				<p>
					<label>host :<input type="text" name="host"
						value="10.10.30.134">
					</label> <label>port :<input type="text" name="port" value="61616">
					</label>
				</p>


				<p>
					<label>topic name :<input type="text" name="topicname"
						value="test">
					</label> <label>Client ID :<input type="text" name="clientid"
						value="12345">
					</label>
				</p>


				<p>
					<label>username :<input type="text" name="username"
						value="admin">
					</label> <label>password :<input type="text" name="password"
						value="admin">
					</label>
				</p>

			</div>
			<div id="rabbitmqsender" style="display: none;">
				<p>***All the fields are required***</p>

				<p>
					<label>host :<input type="text" name="host"
						value="10.10.40.12">
					</label>
				</p>
				<p>
					<label>queue name :<input type="text" name="qname"
						value="JCG_QUEUE">
					</label> <label>message :<input type="text" name="message"
						value="hello world">
					</label>
				</p>
				<p>
					<label>username :<input type="text" name="username"
						value="admin">
					</label> <label>password :<input type="text" name="password"
						value="password">
					</label>
				</p>
			</div>
			<div id="rabbitmqreceiver" style="display: none;">
				<p>***All the fields are required***</p>

				<p>
					<label>host :<input type="text" name="host"
						value="10.10.40.12">
					</label>
				</p>
				<p>
					<label>queue name :<input type="text" name="qname"
						value="JCG_QUEUE">
					</label>
				</p>
				<p>
					<label>username :<input type="text" name="username"
						value="admin">
					</label> <label>password :<input type="text" name="password"
						value="password">
					</label>
				</p>
			</div>

			<div id="ibmmqsender" style="display: none;">
				<p>***All the fields are required***</p>

				<p>
					<label>host :<input type="text" name="host"
						value="10.10.30.134">
					</label>
				</p>
				<p>
					<label>queue manager :<input type="text" name="qmanager"
						value="QM1">
					</label>
				</p>
				<p>
					<label>username :<input type="text" name="username"
						value="">
					</label> <label>password :<input type="text" name="password"
						value="">
					</label>
				</p>
			</div>

			<div id="ibmmqreceiver" style="display: none;">
				<p>***All the fields are required***</p>

				<p>
					<label>host :<input type="text" name="host"
						value="10.10.30.134">
					</label>
				</p>
				<p>
					<label>queue manager :<input type="text" name="qmanager"
						value="QM1">
					</label>
				</p>
				<p>
					<label>username :<input type="text" name="username"
						value="">
					</label> <label>password :<input type="text" name="password"
						value="">
					</label>
				</p>
			</div>

			<div id="kafkaproducer" style="display: none;">
				<p>***All the fields are required***</p>

				<p>
					<label>host :<input type="text" name="host"
						value="127.0.0.1">
					</label>
				</p>
				<p>
					<label>port :<input type="text" name="port" value="9092">
					</label>
				</p>
				<p>
					<label>topic name :<input type="text" name="topicname"
						value="mytopic">
					</label>
				</p>

			</div>

			<div id="kafkaconsumer" style="display: none;">
				<p>***All the fields are required***</p>

				<p>
					<label>host :<input type="text" name="host"
						value="127.0.0.1">
					</label>
				</p>
				<p>
					<label>port :<input type="text" name="port" value="9092">
					</label>
				</p>
				<p>
					<label>topic name :<input type="text" name="topicname"
						value="mytopic">
					</label>
				</p>

			</div>
			<p>
				<button>Submit</button>
			</p>
		</form>
	</div>
</body>
</html>