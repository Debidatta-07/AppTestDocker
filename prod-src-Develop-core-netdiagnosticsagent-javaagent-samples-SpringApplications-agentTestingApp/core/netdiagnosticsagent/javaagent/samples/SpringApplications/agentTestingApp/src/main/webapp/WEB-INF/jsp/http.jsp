<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!-- Static content -->
<link rel="stylesheet" href="static/css/style.css">
<title>Spring Boot : HTTP</title>
</head>
<body>

	<ul>
		<li><a href="/">Home</a></li>
		<li><a class="active" href="http">HTTP</a></li>
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
		<li><a href="dynamicLog">DYNAMIC LOGGING</a></li>
		<li><a href="hazelcast">HAZELCAST</a></li>
		<li><a href="soap">SOAP</a></li>
		<li><a href="loggermdc">LOGGER MDC</a></li>
		<li><a href='"quotes"'>QUOTED URL</a></li>
		<li><a  href="resttemplate">RESTTEMPLATE</a></li>
	</ul>

	<div style="margin-left: 25%; padding: 1px 16px; height: 500px;">
		<legend>HTTP Services</legend>
		<form class="myForm" method="post"
			enctype="application/x-www-form-urlencoded" action="httpcallout">

			<p>
				<label class="choice"> <input type="radio" name="client"
					value="apache" checked="checked"> Apache Commons
					(org.apache.commons.httpclient.HttpClient)
				</label>
			</p>
			<p>
				<label class="choice"> <input type="radio" name="client"
					value="sun"> Sun (java.net.HttpURLConnection)
				</label>
			</p>
			<p>
				<label class="choice"> <input type="radio" name="client"
					value="okhttp"> OKHTTP (okhttp3.OkHttpClient)
				</label>
			</p>
			<p>
				<label class="choice"> <input type="radio" name="client"
					value="asyncHTTP"> Simple Apache HTTP Async Exchange
					(org.apache.http.impl.nio.client.HttpAsyncClients)
				</label>
			</p>
			<p>
				<label class="choice"> <input type="radio" name="client"
					value="asyncHTTPContent"> Apache HTTP Async Exchange using
					Content Exchange (org.apache.http.impl.nio.client.HttpAsyncClients)
				</label>
			</p>
			<p>
				<label>URL <input type="text" name="url"
					value="http://httpbin.org/get" required>
				</label>
			</p>
			<p>
				<button>Submit</button>
			</p>
		</form>

		<form class="myForm" method="post"
			enctype="application/x-www-form-urlencoded" action="httpcallout">
			<p>
				<label class="choice"> <input type="radio" name="client"
					value="googleHTTP"> Google Api (com.google.api.client.http)

				</label>
			</p>
			<p>
				<label>URL <input type="text" name="url"
					value="https://api.github.com/users" required>
				</label>
			</p>
			<p>
				<button>Submit</button>
			</p>
		</form>
		
		<form class="myForm" method="post"
			enctype="application/x-www-form-urlencoded" action="httpcallout">
			<p>
				<label class="choice"> <input type="radio" name="client"
					value="axisHTTP"> Apache Axis (org.apache.axis.transport.http.HTTPSender)

				</label>
			</p>
			<p>
				<label>URL <input type="text" name="url"
					value="http://www.dneonline.com/calculator.asmx" required>
				</label>
			</p>
			<p>
				<button>Submit</button>
			</p>
		</form>

		<form class="myForm" method="post" action="asyncHTTPCallback">
			<p>Apache async HTTP exchange using a callback interface</p>

			<p>
				<label>URL <input type="text" name="url"
					value="http://httpbin.org/get" required>
				</label>
			</p>
			<p>
				<button>Submit</button>
			</p>
		</form>

		<form class="myForm" method="get"
			enctype="application/x-www-form-urlencoded"
			action="asyncHTTPCallback">

			<p>Asynchronous Servlet 3.0</p>
			<p>
				<button>Submit</button>
			</p>

		</form>

	</div>
</body>
</html>