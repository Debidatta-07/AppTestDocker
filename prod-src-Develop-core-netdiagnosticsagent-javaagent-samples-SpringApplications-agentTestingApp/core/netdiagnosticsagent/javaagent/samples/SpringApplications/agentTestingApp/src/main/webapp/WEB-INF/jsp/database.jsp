<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!-- Static content -->
<link rel="stylesheet" href="static/css/style.css">
<script type="text/javascript" src="static/js/app.js"></script>
<title>Spring Boot : DATABASES</title>
</head>
<body>

	<ul>
		<li><a href="/">Home</a></li>
		<li><a href="http">HTTP</a></li>
		<li><a class="active" href="database">DATABASES</a></li>
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

	<div style="margin-left: 25%; padding: 1px 16px;">
		<form class="myForm" method="post" action="dbcallout">

			<fieldset>
				<legend>Database Services</legend>
				<p>
					<label class="choice"> <input type="radio" name="client"
						value="cloudant" checked="checked" onchange="cloudant()" />IBM
						Cloudant
					</label>
				</p>
				<p>
					<label class="choice"> <input type="radio" name="client"
						value="db2" onchange="db2()" /> IBM DB2
					</label>
				</p>
				<p>
					<label class="choice"> <input type="radio" name="client"
						value="mssql" onchange="mssql()" /> SQL Server
					</label>
				</p>
				<p>
					<label class="choice"> <input type="radio" name="client"
						value="mysql" onchange="mysql()" /> Mysql
					</label>
				</p>
				<p>
					<label class="choice"> <input type="radio" name="client"
						value="oracle" onchange="oracle()" /> Oracle Database
					</label>
				</p>
				<p>
					<label class="choice"> <input type="radio" name="client"
						value="postgres" onchange="postgres()" /> PostgreSQL
					</label>
				</p>
				<p>
					<label class="choice"> <input type="radio" name="client"
						value="lettuce" onchange="lettuce()" /> Redis Lettuce
					</label>
				</p>
				<p>
					<label class="choice"> <input type="radio" name="client"
						value="redissyncclient" onchange="redissyncclient()" /> Redis
						Sync Client
					</label>
				</p>
				<p>
					<label class="choice"> <input type="radio" name="client"
						value="redisasyncclient" onchange="redisasyncclient()" /> Redis
						Async Client
					</label>
				</p>

				<p>
					<label class="choice"> <input type="radio" name="client"
						value="cassandraclient" onchange="cassandraclient()" /> Cassandra
						DB
					</label>
				</p>
				<p>
					<label class="choice"> <input type="radio" name="client"
						value="mongoclient" onchange="mongoclient()" /> MONGO DB
					</label>
				</p>
			</fieldset>

			<div id="cloudant">
				<!--  <p>
					<label>Enter the Cloudant DB name : <input type="text"
						name="dbname" value="cavisson" />
					</label>
				</p>  -->
				<p>
					<button>Submit</button>
				</p>

			</div>

			<div id="db2" style="display: none;">
				<p>
					* Driver class : com.ibm.db2.jcc.DB2Driver<br>* Table by
					default : people(firstname,lastname)<br>
				</p>

				<p>
					<label>Database Server Url : <input type="text" name="url"
						value="127.0.0.1:50000" />
					</label> <label>Database Name : <input type="text" name="dbname" /></label>
				</p>
				<p>
					<label>Database Username : <input type="text"
						name="username" />
					</label> <label>Database Password : <input type="text"
						name="password" />
					</label>
				</p>
				<p>
					<label>Firstname : <input type="text" name="firstname"
						value="Sam" />
					</label> <label>Lastname : <input type="text" name="lastname"
						value="Wise" />
					</label>
				</p>
				<p>
					<button>Submit</button>
				</p>

			</div>
			<div id="mssql" style="display: none;">
				<p>
					* Driver Class : com.microsoft.sqlserver.jdbc.SQLServerDriver<br>*
					Table by default : people(firstname,lastname)<br>
				</p>

				<p>
					<label class="choice"> <input type="radio" name="prepared"
						value="yes" checked="checked" />&nbsp;Prepared&nbsp;&nbsp;&nbsp;&nbsp;
					</label> <label class="choice"> <input type="radio" name="prepared"
						value="no" />&nbsp;Non-Prepared&nbsp;&nbsp;&nbsp;
					</label>
				</p>

				<p>
					<label>Database Server Host : <input type="text"
						name="host" value="127.0.0.1" />
					</label> <label>Database Server Port : <input type="text"
						name="port" value="1433" />
					</label> <label>Database Name : <input type="text" name="dbname"
						value="user" />
					</label>
				</p>

				<p>
					<label>Database Username : <input type="text"
						name="username" value="root" />
					</label> <label>Database Password : <input type="text"
						name="password" value="root" />
					</label>
				</p>
				<p>
					<label>Firstname : <input type="text" name="firstname"
						value="Sam" />
					</label> <label>Lastname : <input type="text" name="lastname"
						value="Wise" />
					</label>
				</p>
				<p>
					<button>Submit</button>
				</p>

			</div>

			<div id="mysql" style="display: none;">
				<p>
					* Ex. Port : 3306<br>* Driver Class : com.mysql.cj.jdbc.Driver<br>*
					Table by default : people(firstname,lastname)<br>
				</p>

				<p>
					<label class="choice"> <input type="radio" name="prepared"
						value="yes" checked="checked" />&nbsp;Prepared&nbsp;&nbsp;&nbsp;&nbsp;
					</label> <label class="choice"> <input type="radio" name="prepared"
						value="no" />&nbsp;Non-Prepared&nbsp;&nbsp;&nbsp;
					</label>
				</p>

				<p>
					<label>Database Server Host : <input type="text"
						name="host" value="127.0.0.1" />
					</label> <label>Database Name : <input type="text" name="dbname"
						value="user" />
					</label>
				</p>
				<p>
					<label>Database Username : <input type="text"
						name="username" value="root" />
					</label> <label>Database Password : <input type="text"
						name="password" value="admin" />
					</label>
				</p>
				<p>
					<label>Firstname : <input type="text" name="firstname"
						value="Sam" />
					</label> <label>Lastname : <input type="text" name="lastname"
						value="Wise" />
					</label>
				</p>
				<p>
					<button>Submit</button>
				</p>

			</div>

			<div id="oracle" style="display: none;">
				<p>
					* Driver Class : oracle.jdbc.driver.OracleDriver<br>* Table by
					default : student(firstname,lastname)<br>
				</p>

				<p>
					<label class="choice"> <input type="radio" name="prepared"
						value="yes" checked="checked" />&nbsp;Prepared&nbsp;&nbsp;&nbsp;&nbsp;
					</label> <label class="choice"> <input type="radio" name="prepared"
						value="no" />&nbsp;Non-Prepared&nbsp;&nbsp;&nbsp;
					</label>
				</p>

				<p>
					<label>Database Server Host : <input type="text"
						name="host" value="10.10.40.12" />
					</label> <label>Database Server Port : <input type="text"
						name="port" value="49161" />
					</label>
				</p>
				<p>
					<label>Database Username : <input type="text"
						name="username" value="system" />
					</label> <label>Database Password : <input type="text"
						name="password" value="oracle" />
					</label> <label>Type : <input type="text" name="type" value="XE" />
					</label>
				</p>
				<p>
					<label>Firstname : <input type="text" name="firstname"
						value="Sam" />
					</label> <label>Lastname : <input type="text" name="lastname"
						value="Wise" />
					</label>
				</p>
				<p>
					<button>Submit</button>
				</p>

			</div>
			<div id="postgres" style="display: none;">
				<p>* Ex. Port : 3306*</p>
				Driver Class : org.postgresql.Driver<br>* Table by default :
				people(firstname,lastname)<br>
				<p>
					<label class="choice"> <input type="radio" name="prepared"
						value="yes" checked="checked" />&nbsp;Prepared&nbsp;&nbsp;&nbsp;&nbsp;
					</label> <label class="choice"> <input type="radio" name="prepared"
						value="no" />&nbsp;Non-Prepared&nbsp;&nbsp;&nbsp;
					</label>
				</p>
				<p>
					<label>Database Server Host : <input type="text"
						name="host" value="127.0.0.1" />
					</label> <label>Database Name : <input type="text" name="dbname"
						value="user1" />
					</label>
				</p>
				<p>
					<label>Database Username : <input type="text"
						name="username" value="postgres" />
					</label> <label>Database Password : <input type="text"
						name="password" value="postgres" />
					</label>
				</p>
				<p>
					<label>Firstname : <input type="text" name="firstname"
						value="Sam" />
					</label> <label>Lastname : <input type="text" name="lastname"
						value="Wise" />
					</label>
				</p>
				<p>
					<button>Submit</button>
				</p>

			</div>

			<div id="lettuce" style="display: none;">
				<p>
					* Ex. Port : 6379<br>* Driver Class :
					com.lambdaworks.redis.RedisClient
				</p>
				<p>
					<label>Database Server Host : <input type="text"
						name="host" value="127.0.0.1" />
					</label>
				</p>
				<p>
					<button>Submit</button>
				</p>
			</div>
			<div id="redissyncclient" style="display: none;">
				<p>
					<label>Server Host : <input type="text" name="host"
						value="10.10.40.12" />
					</label> <label>Server PORT : <input type="text" name="port"
						value="6379" />
					</label>
				</p>
				<p>
					<button>Submit</button>
				</p>
			</div>
			<div id="redisasyncclient" style="display: none;">
				<p>
					<label>Server Host : <input type="text" name="host"
						value="10.10.40.12" />
					</label> <label>Server PORT : <input type="text" name="port"
						value="6379" />
					</label>
				</p>
				<p>
					<button>Submit</button>
				</p>

			</div>
			<div id="cassandraclient" style="display: none;">
				<p>* Port : 9042 *</p>
				<p>
					<label class="choice"> <input type="radio" name="version"
						value="three" checked="checked" />&nbsp;Version :
						3.1.1&nbsp;&nbsp;&nbsp;&nbsp;
					</label> <label class="choice"> <input type="radio" name="version"
						value="four" />&nbsp;Version : 4.0.1&nbsp;&nbsp;&nbsp;
					</label>
				</p>
				<p>
					<label>Server Host : <input type="text" name="host"
						value="127.0.0.1" />
					</label>
				</p>
				<p>
					<button>Submit</button>
				</p>

			</div>
		</form>
		<form class="myForm" method="post"
			enctype="application/x-www-form-urlencoded" action="dbcallout">

			<div id="mongoclient" style="display: none;">
				<p>* Port : 27017 *</p>
				<p>
					<label>Server Host :<input type="text" name="serverName"
						id="serverId" value="10.10.40.12"></label> <label>Database
						Name : <input type="text" name="databaseName" id="dbId"
						value="test">
					</label> <label>Collection Name : <input type="text"
						name="collectionName" id="userId" value="Demo"></label>
				</p>
				<p>
					<input type="submit" name="client" value="insert"> <input
						type="submit" name="client" value="update"> <input
						type="submit" name="client" value="deleteFirst"> <input
						type="submit" name="client" value="deleteAll"> <input
						type="submit" name="client" value="getCount"> <input
						type="submit" name="client" value="distinct"> <input
						type="submit" name="client" value="group"> <input
						type="submit" name="client" value="aggregate"> <input
						type="submit" name="client" value="mapreduce"> <input
						type="submit" name="client" value="createindex"> <input
						type="submit" name="client" value="findandmodify"> <input
						type="submit" name="client" value="drop"> <input
						type="submit" name="client" value="dropindex"> <input
						type="submit" name="client" value="parallelscan">
				</p>

				<p>
					<label>New Collection Name :<input type="text"
						name="newCollectionName" id="newCollId" value="Demo2"></label>
				</p>

				<p>
					<input type="submit" name="client" value="rename">
				</p>

			</div>
		</form>
	</div>
</body>
</html>