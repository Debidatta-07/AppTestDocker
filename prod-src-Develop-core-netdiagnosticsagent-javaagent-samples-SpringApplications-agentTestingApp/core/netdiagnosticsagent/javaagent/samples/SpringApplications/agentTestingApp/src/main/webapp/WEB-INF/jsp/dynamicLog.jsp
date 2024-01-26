<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!-- Static content -->
<link rel="stylesheet" href="static/css/style.css">
<title>Spring Boot : DYNAMIC LOGGING</title>
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
		<li><a href="memcached">MEMCACHED</a></li>
		<li><a href="restlet">RESTLET</a></li>
		<li><a class="active" href="dynamicLog">DYNAMIC LOGGING</a></li>
		<li><a href="hazelcast">HAZELCAST</a></li>
		<li><a href="soap">SOAP</a></li>
		<li><a href="loggermdc">LOGGER MDC</a></li>
		<li><a href='"quotes"'>QUOTED URL</a></li>
		<li><a  href="resttemplate">RESTTEMPLATE</a></li>
	</ul>

	<div style="margin-left: 25%; padding: 1px 16px; height: 500px;">
		<form class="myForm" method="post"
			enctype="application/x-www-form-urlencoded" action="dlcallout">

			<fieldset>
				<legend>Dynamic Logging Services</legend>
				<p>
					<label class="choice"> <input type="radio" name="client"
						value="LambdaReturn" checked="checked">Call Method with
						Lambda expresion , local var ,class field and Return value
					</label>
				</p>
				<p>
					<label class="choice"> <input type="radio" name="client"
						value="voidmethod">Call Method with Return type Void
					</label>
				</p>
				<p>
					<label class="choice"> <input type="radio" name="client"
						value="anonymousPCthread">Call Method with Anonymous class
						and Parent Child Thread
					</label>
				</p>

			</fieldset>
			<br>
			<p>
				<button>Submit</button>
			</p>
		</form>
	</div>
</body>
</html>