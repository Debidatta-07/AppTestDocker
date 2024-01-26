<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!-- Static content -->
<link rel="stylesheet" href="static/css/style.css">
<title>Spring Boot : MEMCACHED</title>
</head>
<body>

	<ul>
		<li><a href="/">Home</a></li>
		<li><a href="http">HTTP</a></li>
		<li><a href="database">DATABASES</a></li>
		<li><a href="thread">THREADS</a></li>
		<li><a href="ldap">LDAP</a></li>
		<li><a href="jms">JMS</a></li>
		<li><a href="completable">COMPLETABLE FUTURE</a></li>
		<li><a href="exception">EXCEPTIONS</a></li>
		<li><a href="reactive">REACTIVE</a></li>
		<li><a href="solr">APACHE SOLR</a></li>
		<li><a href="reqres">REQUEST RESPONSE DUMP</a></li>
		<li><a class="active" href="memcached">MEMCACHED</a></li>
		<li><a href="restlet">RESTLET</a></li>
		<li><a href="dynamicLog">DYNAMIC LOGGING</a></li>
		<li><a href="hazelcast">HAZELCAST</a></li>
		<li><a href="soap">SOAP</a></li>
		<li><a href="loggermdc">LOGGER MDC</a></li>	
		<li><a href='"quotes"'>QUOTED URL</a></li>
		<li><a  href="resttemplate">RESTTEMPLATE</a></li>
	</ul>

	<div style="margin-left: 25%; padding: 1px 16px; height: 500px;">

		<form class="myForm" method="post"
			enctype="application/x-www-form-urlencoded" action="memcachedcallout">

			<fieldset>
				<legend>Memcached Service</legend>
				<p>* Port : 11211 *</p>

				<p>
					<label class="choice"> <input type="radio" name="client"
						value="Memcached" checked="checked"> Run Memcached Client

					</label>
				</p>
			</fieldset>
			<p>
				<label>Host <input type="text" name="host"
					value="10.10.30.134" required></label>
			</p>

			<p>
				<button>Submit</button>
			</p>

		</form>
	</div>
</body>
</html>