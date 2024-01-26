<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!-- Static content -->
<link rel="stylesheet" href="static/css/style.css">
<title>Spring Boot : REACTIVE</title>
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
		<li><a class="active" href="reactive">REACTIVE</a></li>
		<li><a href="solr">APACHE SOLR</a></li>
		<li><a href="reqres">REQUEST RESPONSE DUMP</a></li>
		<li><a href="memcached">MEMCACHED</a></li>
		<li><a href="restlet">RESTLET</a></li>
		<li><a href="dynamicLog">DYNAMIC LOGGING</a></li>
		<li><a href="hazelcast">HAZELCAST</a></li>
		<li><a href="soap">SOAP</a></li>
		<li><a href="loggermdc">LOGGER MDC</a></li>	
		<li><a href='"quotes"'>QUOTED URL</a></li>
		<li><a  href="resttamplate">RASTTPLATE</a></li>
	</ul>

	<div style="margin-left: 25%; padding: 1px 16px; height: 500px;">
		<form class="myForm" method="post"
			enctype="application/x-www-form-urlencoded" action="reactive/abc">


			<fieldset>
				<legend>Redis Services</legend>
				<p>** Host : 10.10.30.134 ** ** Port : 6379 **</p>
				<p>
					<label>Enter the ID <input type="text" name="id" value="25"
						required>
					</label> <label>Enter the full name <input type="text"
						name="fullName" value="fullName" required>
					</label>
				</p>
				<p>
					<button>POST</button>
				</p>
		</form>
		<form class="myForm" method="get"
			enctype="application/x-www-form-urlencoded" action="reactive/abc">


			<p>
				<button>GET ID</button>
			</p>
			</fieldset>
		</form>
		<form class="myForm" method="post"
			enctype="application/x-www-form-urlencoded"
			action="webfluxHTTPCallout">

			<fieldset>
				<legend>Webflux Services</legend>
				<p>
					<label class="choice"> <input type="radio"
						name="wordOrSentence" value="word" checked="checked"> Word
						~ Webflux HTTP
					</label>
				</p>


				<p>
					<label>Enter a word <input type="text" name="word"
						value="cool" required>
					</label>
				</p>
				<p>
					<button>Submit</button>
				</p>
			</fieldset>
		</form>



		<form class="myForm" method="post"
			enctype="application/x-www-form-urlencoded" action="tweets">

			<fieldset>
				<legend>Mongo Services</legend>
				<p>** Host : 10.10.40.12 ** ** Port : 27017 **</p>
				<p>Create Tweet</p>
				<p>
					<label>tweet id :<input type="text" name="id" value="1">
					</label> <label>tweet message : <input type="text" name="text"
						value="test">
					</label>
				</p>
				<button>Submit</button>
		</form>



		<form class="myForm" method="get"
			enctype="application/x-www-form-urlencoded" action="tweets">

			<p>Get ALL Tweets</p>

			<button>Submit</button>

		</form>


		<form class="myForm" method="get"
			enctype="application/x-www-form-urlencoded" action="tweetsbyid">

			<p>Get Tweet By ID</p>

			<p>
				<label>tweet id :<input type="text" name="id" value="1">
				</label>
			</p>

			<button>Submit</button>
			<p>***For Put and Delete method use /tweets/{id} from POSTMAN
				Tool***</p>
			</fieldset>

		</form>

	</div>
</body>
</html>