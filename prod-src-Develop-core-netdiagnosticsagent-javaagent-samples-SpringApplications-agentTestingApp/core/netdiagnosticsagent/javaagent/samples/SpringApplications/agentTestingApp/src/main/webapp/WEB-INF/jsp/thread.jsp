<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!-- Static content -->
<link rel="stylesheet" href="static/css/style.css">
<title>Spring Boot : THREADS</title>
</head>
<body>

	<ul>
		<li><a href="/">Home</a></li>
		<li><a href="http">HTTP</a></li>
		<li><a href="database">DATABASES</a></li>
		<li><a class="active" href="thread">THREADS</a></li>
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
		<form class="myForm" method="post"
			enctype="application/x-www-form-urlencoded" action="threadcallout">

			<fieldset>
				<legend>Thread Services</legend>
				<p>
					<label class="choice"> <input type="radio" name="client"
						value="fork" checked="checked"> Fork Join Thread
					</label>
				</p>
				<p>
					<label class="choice"> <input type="radio" name="client"
						value="simple"> Simple Thread Pool Executor
					</label>
				</p>
				<p>
					<label class="choice"> <input type="radio" name="client"
						value="scheduledRunnable"> Scheduled Thread Pool Executor
						Using Runnable Interface
					</label>
				</p>
				<p>
					<label class="choice"> <input type="radio" name="client"
						value="scheduledCallable"> Scheduled Thread Pool Executor
						Using Callable Interface
					</label>
				</p>


				<p>
					<label class="choice"> <input type="radio" name="client"
						value="scheduledExecuter"> Scheduled Thread Pool Using
						Custom Executor Service
					</label>
				</p>


				<p>
					<label class="choice"> <input type="radio" name="client"
						value="makeThreadPool"> Make a thread pool
					</label>
				</p>
				<p>
					<label class="choice"> <input type="radio" name="client"
						value="makeThreadPoolCall"> Make a thread pool call
					</label>
				</p>
				<p>
					<label class="choice"> <input type="radio" name="client"
						value="makeThreadPoolUsingExecutor"> Make a Thread Call
						Using Executor
					</label>
				</p>
				<p>
					<label class="choice"> <input type="radio" name="client"
						value="makeQuartzCall"> Make a Quartz Job Call
					</label>
				</p>

				<p>
					<label class="choice"> <input type="radio" name="client"
						value="RxjavaSyncCall"> Rx java Sync Call
					</label>
				</p>

				<p>
					<label class="choice"> <input type="radio" name="client"
						value="RxjavaAsyncCall"> Rx java Async Call
					</label>
				</p>


				<p>
					<label class="choice"> <input type="radio" name="client"
						value="RxjavaAsyncTheadCall"> Rx java Async on Singe
						Thread Call
					</label>
				</p>

				<p>
					<label class="choice"> <input type="radio" name="client"
						value="RxjavaAsyncMultiTheadCall"> Rx java Async on Multi
						Thread Call
					</label>
				</p>
				<p>
					<label class="choice"> <input type="radio" name="client"
						value="mutexlockCall"> Mutex Lock Using Thread
					</label>
				</p>
				<p>
					<label class="choice"> <input type="radio" name="client"
						value="ProdConsCall"> Start Producer Consumer
					</label>
				</p>
				<p>
					<label class="choice"> <input type="radio" name="client"
						value="MultiProdConCall"> Start Multiple Producer Consumer
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