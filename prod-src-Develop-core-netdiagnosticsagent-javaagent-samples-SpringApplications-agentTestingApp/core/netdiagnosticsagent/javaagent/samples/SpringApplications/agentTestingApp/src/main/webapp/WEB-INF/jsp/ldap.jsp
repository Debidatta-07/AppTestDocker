<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!-- Static content -->
<link rel="stylesheet" href="static/css/style.css">
<script type="text/javascript" src="static/js/app.js"></script>
<title>Spring Boot : LDAP</title>
</head>
<body>
	<ul>
		<li><a href="/">Home</a></li>
		<li><a href="http">HTTP</a></li>
		<li><a href="database">DATABASES</a></li>
		<li><a href="thread">THREADS</a></li>
		<li><a class="active" href="ldap">LDAP</a></li>
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
		<form class="myForm" method="post" action="ldapcallout">

			<fieldset>
				<legend>LDAP Services</legend>
				<p>
					<label class="choice"> <input type="radio" name="client"
						value="add" checked="checked" onchange="add()" />ADD

					</label>
				</p>
				<p>
					<label class="choice"> <input type="radio" name="client"
						value="fetch" onchange="fetch()" />FETCH
					</label>
				</p>
				<p>
					<label class="choice"> <input type="radio" name="client"
						value="delete" onchange="deleteById()" />DELETE
					</label>
				</p>
				<p>
					<label class="choice"> <input type="radio" name="client"
						value="modify" onchange="modify()" />MODIFY
					</label>
				</p>
				<p>
					<label class="choice"> <input type="radio" name="client"
						value="moddn" onchange="moddn()" />MODDN
					</label>
				</p>
				<p>
					<label class="choice"> <input type="radio" name="client"
						value="compare" onchange="compare()" />COMPARE
					</label>
				</p>
				<p>
					<label class="choice"> <input type="radio" name="client"
						value="extendedStartTls" onchange="extendedStartTls()" />EXTENDED
						START TLS
					</label>
				</p>

			</fieldset>


			<div id="add">
				<p>***Use Apache Directory Studio Server***</p>

				<p>***All the fields are required***</p>
				<p>
					<label>host :<input type="text" name="host"
						value="localhost">
					</label>
				</p>

				<p>
					<label>port :<input type="text" name="port" value="10389">
					</label>
				</p>
				<p>
					<label>organisation :<input type="text" name="org"
						value="Company">
					</label>
				</p>
				<p>
					<label>organisation unit :<input type="text" name="orgunit"
						value="users">
					</label>
				</p>
				<p>
					<label>Employee No. :<input type="text" name="empno"
						placeholder="Employee No.">
					</label>
				</p>
				<p>
					<label>First Name :<input type="text" name="firstname"
						placeholder="First Name">
					</label>
				</p>
				<p>
					<label>Last Name :<input type="text" name="lastname"
						placeholder="Surname">
					</label>
				</p>


			</div>

			<div id="fetch" style="display: none;">
				<p>***All the fields are required***</p>
				<p>
					<label>host :<input type="text" name="host"
						value="localhost">
					</label>
				</p>

				<p>
					<label>port :<input type="text" name="port" value="10389">
					</label>
				</p>
				<p>
					<label>organisation :<input type="text" name="org"
						value="Company">
					</label>
				</p>
				<p>
					<label>organisation unit :<input type="text" name="orgunit"
						value="users">
					</label>
				</p>

			</div>
			<div id="delete" style="display: none;">
				<p>***All the fields are required***</p>
				<p>
					<label>host :<input type="text" name="host"
						value="localhost">
					</label>
				</p>

				<p>
					<label>port :<input type="text" name="port" value="10389">
					</label>
				</p>
				<p>
					<label>organisation :<input type="text" name="org"
						value="Company">
					</label>
				</p>
				<p>
					<label>organisation unit :<input type="text" name="orgunit"
						value="users">
					</label>
				</p>
				<p>
					<label>Employee No. :<input type="text" name="empno"
						placeholder="Employee No.">
					</label>
				</p>

			</div>
			<div id="modify" style="display: none;">
				<p>***All the fields are required***</p>
				<p>
					<label>host :<input type="text" name="host"
						value="localhost">
					</label>
				</p>

				<p>
					<label>port :<input type="text" name="port" value="10389">
					</label>
				</p>
				<p>
					<label>organisation :<input type="text" name="org"
						value="Company">
					</label>
				</p>
				<p>
					<label>organisation unit :<input type="text" name="orgunit"
						value="users">
					</label>
				</p>
				<p>
					<label>Employee No. :<input type="text" name="empno"
						placeholder="Employee No.">
					</label>
				</p>
				<p>
					<label>First Name :<input type="text" name="firstname"
						placeholder="First Name">
					</label>
				</p>
				<p>
					<label>Last Name :<input type="text" name="lastname"
						placeholder="Surname">
					</label>
				</p>


			</div>
			<div id="moddn" style="display: none;">
				<p>***All the fields are required***</p>
				<p>
					<label>host :<input type="text" name="host"
						value="localhost">
					</label>
				</p>

				<p>
					<label>port :<input type="text" name="port" value="10389">
					</label>
				</p>
				<p>
					<label>organisation :<input type="text" name="org"
						value="Company">
					</label>
				</p>
				<p>
					<label>organisation unit :<input type="text" name="orgunit"
						value="users">
					</label>
				</p>
				<p>
					<label>Employee No. :<input type="text" name="empno"
						placeholder="Employee No.">
					</label>
				</p>
				<p>
					<label>New Path :<input type="text" name="newpath"
						placeholder="New Path for the entry">
					</label>
				</p>


			</div>
			<div id="compare" style="display: none;">
				<p>***All the fields are required***</p>
				<p>
					<label>host :<input type="text" name="host"
						value="localhost">
					</label>
				</p>

				<p>
					<label>port :<input type="text" name="port" value="10389">
					</label>
				</p>
				<p>
					<label>organisation :<input type="text" name="org"
						value="Company">
					</label>
				</p>
				<p>
					<label>organisation unit :<input type="text" name="orgunit"
						value="users">
					</label>
				</p>
				<p>
					<label>Employee No. :<input type="text" name="empno"
						placeholder="Employee No.">
					</label>
				</p>

			</div>
			<div id="extendedStartTls" style="display: none;">
				<p>***All the fields are required***</p>
				<p>
					<label>host :<input type="text" name="host"
						value="localhost">
					</label>
				</p>
				<p>
					<label>port :<input type="text" name="port" value="10389">
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