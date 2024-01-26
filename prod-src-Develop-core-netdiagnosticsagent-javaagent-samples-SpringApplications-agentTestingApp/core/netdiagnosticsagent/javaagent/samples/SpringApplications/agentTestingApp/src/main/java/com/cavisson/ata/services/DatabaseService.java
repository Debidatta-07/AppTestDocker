package com.cavisson.ata.services;

/**
 * @author Vishal Singh
 *
 */

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import com.cloudant.client.api.ClientBuilder;
import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;
import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.RedisFuture;
import com.lambdaworks.redis.api.StatefulRedisConnection;
import com.lambdaworks.redis.api.async.RedisAsyncCommands;
import com.lambdaworks.redis.api.sync.RedisCommands;
import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.Cursor;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.GroupCommand;
import com.mongodb.MapReduceCommand;
import com.mongodb.MapReduceOutput;
import com.mongodb.MongoClient;
import com.mongodb.ParallelScanOptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.cavisson.ata.utils.CassandraConnector;
import com.cavisson.ata.utils.BookRepository;
import com.cavisson.ata.utils.KeyspaceRepository;
import com.cavisson.ata.main.Application;
import com.cavisson.ata.models.Book;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.utils.UUIDs;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.*;
import java.net.InetSocketAddress;

@SuppressWarnings("deprecation")
public class DatabaseService {

	// cloudant db
	public static String cloudantconnect(String dbname) {
		String username = "0146e259-2907-42de-8d81-b9f9790e81b6-bluemix";
		String password = "8c505da4df4f82e7190cb8789ae0b4598854246742cf45834acb8d20c998e70c";
		CloudantClient client;
		try {
			client = ClientBuilder.url(new URL(
					"https://0146e259-2907-42de-8d81-b9f9790e81b6-bluemix:8c505da4df4f82e7190cb8789ae0b4598854246742cf45834acb8d20c998e70c@0146e259-2907-42de-8d81-b9f9790e81b6-bluemix.cloudantnosqldb.appdomain.cloud"))
					.username(username).password(password).build();

			System.out.println("instance created");
			// Show the server version
			System.out.print(", server version = " + client.serverVersion());

			// Create a new database.
			client.createDB("cavisson");
			System.out.print(", database " + "cavisson" + " created");

			// Get a List of all the databases this Cloudant account
			List<String> databases = client.getAllDbs();
			String datab = null;
			System.out.println("All my databases : ");
			for (String db : databases) {
				System.out.print(db);
				datab = db;
				break;
			}

			com.google.gson.JsonObject studentJson = new com.google.gson.JsonObject();
			studentJson.addProperty("_id", generateId());
			studentJson.addProperty("firstname", "Joe");
			studentJson.addProperty("lastname", "Doe");

			// Get a Database instance to interact with, but don't create it if it doesn't
			// already exist
			Database db = client.database(datab, false);

			com.cloudant.client.api.model.Response dbResponse = db.save(studentJson);
			com.google.gson.JsonObject output = new com.google.gson.JsonObject();
			// for success insertion
			if (dbResponse.getStatusCode() < 400) {
				output.add("document", studentJson);

				// dbResponse json data
				com.google.gson.JsonObject dbResponseJson = new com.google.gson.JsonObject();
				dbResponseJson.addProperty("status", dbResponse.getStatusCode() + " - " + dbResponse.getReason());
				dbResponseJson.addProperty("id", dbResponse.getId());
				dbResponseJson.addProperty("rev", dbResponse.getRev());

				output.add("data", dbResponseJson);
				System.out.println("You have inserted the document");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				db.find(dbResponse.getId());
				System.out.println(output);

				// Delete a database we created previously.
				client.deleteDB(datab);
				System.out.println("database " + datab + " deleted");
			} else {
				output.addProperty("err", dbResponse.getStatusCode() + " - " + dbResponse.getReason());
			}
			return "cloudant db instance created";

		} catch (Exception e) {
			System.err.println(e);
		}
		return "failed";
	}

	private static String generateId() {
		return "ID#" + new Double(Math.floor(Math.random() * 10000)).intValue();
	}

	// ibm db2
	public static String db2connect(String url, String dbname, String username, String password, String firstName,
			String lastName) {
		ArrayList<String> list = new ArrayList<String>();
		list.add("table before deletion");
		try {

			Class.forName("com.ibm.db2.jcc.DB2Driver");
			String str = "jdbc:db2://" + url + "/" + dbname;
			Connection con = DriverManager.getConnection(str, username, password);

			PreparedStatement stmt = con.prepareStatement("insert into people values(?,?)");
			stmt.setString(1, firstName);// 1 specifies the first parameter in the query
			stmt.setString(2, lastName);

			stmt.executeUpdate();

			String sql1 = "update people set firstname=? where lastname=?";

			PreparedStatement preparedStatement1 = con.prepareStatement(sql1);

			preparedStatement1.setString(1, "changed");
			preparedStatement1.setString(2, lastName);
			preparedStatement1.executeUpdate();

			String sql2 = "select * from people";
			PreparedStatement preparedStatement2 = con.prepareStatement(sql2);
			ResultSet rs2 = preparedStatement2.executeQuery();
			while (rs2.next()) {
				list.add(rs2.getString(1) + " " + rs2.getString(2));

			}
			list.add("table after deletion");

			String sql3 = "delete from people where firstname=?";
			PreparedStatement preparedStatement3 = con.prepareStatement(sql3);
			preparedStatement3.setString(1, firstName);
			preparedStatement3.executeUpdate();

			while (rs2.next()) {
				list.add(rs2.getString(1) + " " + rs2.getString(2));

			}
			System.out.println("connected to db2, url = " + str + " , executing queries, db successfully updated...");

			con.close();
		}

		catch (Exception e) {
			System.err.println(e);
		}

		return list.toString();
	}

	// mssql prepared
	public static String mssqlPreparedConnect(String host, String username, String password, String dbname, String port,
			String firstname, String lastname) {
		ArrayList<String> s = new ArrayList<String>();
		s.add("data before deletion : ");
		try {
			String connUrl = "jdbc:sqlserver://" + host + ":" + port + ";databaseName=" + dbname;
			Connection con = null;
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connUrl, username, password);

			PreparedStatement ps1 = con.prepareStatement("insert into people values(?,?)");
			ps1.setString(1, firstname);
			ps1.setString(2, lastname);
			ps1.executeUpdate();

			PreparedStatement ps2 = con.prepareStatement("SELECT * FROM people");
			ResultSet rs = ps2.executeQuery();
			while (rs.next()) {
				s.add(rs.getString("firstName") + " " + rs.getString("lastName"));
			}

			PreparedStatement ps3 = con.prepareStatement("delete from people where firstname=?");
			ps3.setString(1, firstname);
			ps3.executeUpdate();

			rs = ps2.executeQuery();
			while (rs.next()) {
				s.add(rs.getString("firstName") + " " + rs.getString("lastName"));
			}
			System.out.println("connected to mssql, url = " + connUrl
					+ " , executing prepared queries, db successfully updated...");
			con.close();

		}

		catch (Exception e) {
			System.err.println(e);
		}
		return s.toString();
	}

	// mssql non prepared
	public static String mssqlNonPrepConnect(String host, String username, String password, String dbname,
			String port) {
		ArrayList<String> list = new ArrayList<String>();
		list.add("data before deletion : ");
		try {
			String connUrl = "jdbc:sqlserver://" + host + ":" + port + ";databaseName=" + dbname;
			Connection con = null;
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connUrl, username, password);

			Statement stm = con.createStatement();

			String SQL = "INSERT INTO people  " + "VALUES ('Sam','Wise')";
			stm.executeUpdate(SQL);

			String sql = "SELECT * from people";
			ResultSet rs2 = stm.executeQuery(sql);

			while (rs2.next()) {
				list.add(rs2.getString(1) + " " + rs2.getString(2));

			}

			String sql3 = "delete from student where firstname='Sam'";
			stm.executeUpdate(sql3);

			list.add("TABLE AFTER DELETION");

			ResultSet rs1 = stm.executeQuery(sql);

			while (rs1.next()) {
				list.add(rs1.getString(1) + " " + rs1.getString(2));

			}
			System.out.println("connected to mssql, url = " + connUrl
					+ " , executing non prepared queries, db successfully updated...");
			con.close();
		}

		catch (Exception e) {
			System.err.println(e);
		}
		return list.toString();
	}

	public static String mysqlNonPrepConnect(String dbname, String usernameDb, String passwordDb, String host) {
		ArrayList<String> list = new ArrayList<String>();
		list.add("TABLE BEFORE DELETION---------");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String connUrl = "jdbc:mysql://" + host + ":3306/" + dbname;

			Connection con = DriverManager.getConnection(connUrl, usernameDb, passwordDb);

			Statement stm = con.createStatement();

			String SQL = "INSERT INTO people  " + "VALUES ('Sam','Wise')";
			stm.executeUpdate(SQL);

			String sql = "SELECT * from people";
			ResultSet rs2 = stm.executeQuery(sql);

			while (rs2.next()) {
				list.add(rs2.getString(1) + " " + rs2.getString(2));

			}
			String sql3 = "delete from people where firstname='Sam'";
			stm.executeUpdate(sql3);

			list.add("TABLE AFTER DELETION-------");

			ResultSet rs1 = stm.executeQuery(sql);

			while (rs1.next()) {
				list.add(rs1.getString(1) + " " + rs1.getString(2));
			}

			System.out.println("connected to mysql, url = " + connUrl
					+ " , executing non prepared queries, db successfully updated...");
			con.close();

		} catch (Exception e) {
			System.err.println(e);
		}
		return list.toString();
	}

	// mssql prepared
	public static String mysqlPreparedConnect(String dbname, String username, String password, String firstname,
			String lastname, String host) {

		ArrayList<String> mylist = new ArrayList<String>();
		mylist.add("Table before Deletion :-------- ");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String connUrl = "jdbc:mysql://" + host + ":3306/" + dbname;

			Connection con = DriverManager.getConnection(connUrl, username, password);

			PreparedStatement ps = con.prepareStatement("insert into people values(?,?)");

			ps.setString(1, firstname);
			ps.setString(2, lastname);
			ps.executeUpdate();

			PreparedStatement ps2 = con.prepareStatement("select * from people");
			ResultSet rs2 = ps2.executeQuery();
			while (rs2.next()) {

				mylist.add(rs2.getString(1) + "    " + rs2.getString(2));
			}

			PreparedStatement ps1 = con.prepareStatement("delete from people where firstname=?");
			ps1.setString(1, firstname);
			int i1 = ps1.executeUpdate();
			mylist.add("Table after Deletion :----- ");

			ResultSet rs3 = ps2.executeQuery();

			while (rs3.next()) {

				mylist.add(rs3.getString(1) + " " + rs3.getString(2));
			}

			if (i1 > 0)
				System.out.println("connected to mysql, url = " + connUrl
						+ " , executing prepared queries, db successfully updated...");

			con.close();
		} catch (Exception e) {
			System.err.println(e);
		}
		return mylist.toString();
	}

	// oracle prepared
	public static String oraclePreparedConnect(String host, String port, String username, String password,
			String firstname, String lastname, String type) {
		System.out.println("type:" + type);
		ArrayList<String> list = new ArrayList<String>();
		list.add("TABLE BEFORE DELETION:---------");
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@" + host + ":" + port + ":" + type,
					username, password);

			PreparedStatement stmt = con.prepareStatement("insert into student values(?,?)");
			stmt.setString(1, firstname);// 1 specifies the first parameter in the query
			stmt.setString(2, lastname);
			stmt.executeUpdate();

			String sql2 = "select * from student";

			PreparedStatement preparedStatement2 = con.prepareStatement(sql2);
			ResultSet rs2 = preparedStatement2.executeQuery();
			while (rs2.next()) {
				list.add(rs2.getString(1) + " " + rs2.getString(2));

			}

			list.add("TABLE AFTER DELETION:---------");

			String sql3 = "delete from student where firstname=?";
			PreparedStatement preparedStatement3 = con.prepareStatement(sql3);
			preparedStatement3.setString(1, firstname);
			preparedStatement3.executeUpdate();

			rs2 = preparedStatement2.executeQuery();
			while (rs2.next()) {
				list.add(rs2.getString(1) + " " + rs2.getString(2));

			}
			System.out.println("connected to oracle xe db, url : jdbc:oracle:thin:@" + host + ":" + port + ":" + type
					+ " , prepared insertion & deletion successful");
			con.close();

		} catch (Exception e) {
			System.err.println(e);

		}
		return list.toString();
	}

	// oracle non prepared
	public static String oracleNonPrepConnect(String host, String port, String username, String password, String type) {
		System.out.println("type:" + type);
		ArrayList<String> list = new ArrayList<String>();
		list.add("TABLE BEFORE DELETION:----------");
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@" + host + ":" + port + ":" + type,
					username, password);

			Statement stm = con.createStatement();

			String SQL = "INSERT INTO student  " + "VALUES ('Sam', 'Wise')";
			stm.executeUpdate(SQL);

			String sql = "SELECT * from student";

			ResultSet rs2 = stm.executeQuery(sql);

			while (rs2.next()) {
				list.add(rs2.getString(1) + " " + rs2.getString(2));

			}

			list.add("TABLE AFTER DELETION:----------");

			String sql3 = "delete from student where firstname='Sam'";
			stm.executeUpdate(sql3);

			rs2 = stm.executeQuery(sql);

			while (rs2.next()) {
				list.add(rs2.getString(1) + " " + rs2.getString(2));
			}

			System.out.println("connected to oracle xe db, url : jdbc:oracle:thin:@" + host + ":" + port + ":" + type
					+ " , non-prepared insertion & deletion successful");
			con.close();
		} catch (Exception e) {
			System.err.println(e);
		}

		return list.toString();
	}

	// postgres prepared
	public static String postgresPreparedConnect(String host, String dbname, String username, String password,
			String firstname, String lastname) {

		ArrayList<String> mylist = new ArrayList<String>();
		mylist.add("Table before Deletion :--------- ");
		int j = 0;
		System.out.println(firstname + "" + lastname);
		try {
			Class.forName("org.postgresql.Driver");
			String connUrl = "jdbc:postgresql://" + host + ":5432/" + dbname;
			Connection con = DriverManager.getConnection(connUrl, username, password);

			PreparedStatement ps = con.prepareStatement("insert into people values(?,?)");

			ps.setString(1, firstname);
			ps.setString(2, lastname);
			int i = ps.executeUpdate();
			if (i > 0)
				System.out.println("You are successfully registered...");

			PreparedStatement ps2 = con.prepareStatement("select * from people");
			ResultSet rs2 = ps2.executeQuery();

			while (rs2.next()) {
				mylist.add(rs2.getString(1) + " " + rs2.getString(2));
				System.out.println(rs2.getString(1) + " " + rs2.getString(2));

				j++;
			}

			mylist.add("table after deleted :----------");

			PreparedStatement ps1 = con.prepareStatement("delete from people where firstname=?");
			ps1.setString(1, firstname);
			int i1 = ps1.executeUpdate();

			rs2 = ps2.executeQuery();

			while (rs2.next()) {
				mylist.add(rs2.getString(1) + " " + rs2.getString(2));
				System.out.println(rs2.getString(1) + " " + rs2.getString(2));

				j++;
			}

			if (i1 > 0)
				System.out.println("successfully deleted...");

			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mylist.toString();
	}

	// postgres non prepared
	public static String postgresNonPrepConnect(String host, String dbname, String username, String password,
			String firstname, String lastname) {
		ArrayList<String> list = new ArrayList<String>();
		list.add("TBALE BEFORE DELETION:---------");
		try {
			Class.forName("org.postgresql.Driver");
			String connUrl = "jdbc:postgresql://" + host + ":5432/" + dbname;
			Connection con = DriverManager.getConnection(connUrl, username, password);

			Statement stm = con.createStatement();

			String SQL = "INSERT INTO people  " + "VALUES ('sam','wise')";
			stm.executeUpdate(SQL);

			String sql = "SELECT * from people";
			ResultSet rs2 = stm.executeQuery(sql);

			while (rs2.next()) {
				list.add(rs2.getString(1) + " " + rs2.getString(2));

			}

			String sql3 = "delete from people where FirstName='sam'";
			stm.executeUpdate(sql3);

			list.add("TABLE AFTER DELETION:----------");

			String sql1 = "SELECT * from people";
			ResultSet rs1 = stm.executeQuery(sql);

			while (rs1.next()) {
				list.add(rs1.getString(1) + " " + rs1.getString(2));

			}
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "done";
	}

	// redis lettuce
	public static String redisCall(String host) {
		String str = "redis://" + host + ":6379";
		RedisClient redisClient = RedisClient.create(str);
		StatefulRedisConnection<String, String> connection = redisClient.connect();
		RedisCommands<String, String> syncCommands = connection.sync();
		RedisAsyncCommands<String, String> asyncCommands = redisClient.connect().async();

		syncCommands.set("key", "Hello, Redis!");

		RedisFuture<String> future = asyncCommands.get("key");
		String value = "";
		try {
			value = future.get();
		} catch (Exception e) {
			System.err.println(e);
		}

		System.out.println("using redis, url = " + str + " , value = " + value);

		String output = syncCommands.get("key");
		String info = syncCommands.info();
		syncCommands.exists("key");
		syncCommands.ttl("key");

		connection.close();
		redisClient.shutdown();
		return "done " + output + " INFO " + info;
	}

	// redis sync
	public static String RedisSyncClient(String host, String port) {
		String result = "";
		StatefulRedisConnection<String, String> connection = null;
		RedisClient redisClient = null;
		String url = "redis://" + host + ":" + port;
		if (null == redisClient) {
			System.out.println("redisClient Found null ,Hence creating a new connection with " + url);
			redisClient = RedisClient.create(url);
			connection = redisClient.connect();

			System.out.println("Connection obj : " + connection.toString());
		}

		try {
			System.out.println("At the method : connectToRedisClient ");
			// RedisClient redisClient =
			// RedisClient.create("redis://password@localhost:6379/");

			RedisCommands<String, String> syncCommands = connection.sync();
			// System.out.println("Class name : " +syncCommands.getClass().getName());
			syncCommands.set("k1", "Hello, Redis!");
			String value = syncCommands.get("k1");
			result += "<h3>" + "Value for Key k1 is : " + value + "</h3>";
			syncCommands.hset("recordName", "FirstName", "John");
			syncCommands.hset("recordName", "LastName", "Smith");
			Map<String, String> record = syncCommands.hgetall("recordName");
			for (Map.Entry<String, String> entry : record.entrySet()) {
				result += "<h3>" + "Key = " + entry.getKey() + ", Value = " + entry.getValue() + "</h3>";
			}

		} catch (Exception e) {
			e.printStackTrace();
			connection.close();
			redisClient.shutdown();
			redisClient = null;
		}
		return result;
	}

	// redis async
	public static String RedisAsyncClient(String host, String port) {
		int count = 0;
		String result = "";
		StatefulRedisConnection<String, String> connection = null;
		RedisClient redisClient = null;
		String url = "redis://" + host + ":" + port;
		if (null == redisClient) {
			System.out.println("redisClient Found null ,Hence creating a new connection with " + url);
			redisClient = RedisClient.create(url);
			connection = redisClient.connect();

			System.out.println("Connection obj : " + connection.toString());
		}

		try {
			System.out.println("At the method : connectToRedisClient ");

			RedisAsyncCommands<String, String> asyncCommands = connection.async();
			asyncCommands.set("key", "value");
			RedisFuture<String> future = asyncCommands.get("key");
			result += "<h3>" + "Value for Key is : " + future.get() + "</h3>";

			asyncCommands.set("key1", "value1");
			RedisFuture<String> future2 = asyncCommands.get("key1");
			System.out.println(" Applying thenAcceptAsync for Key1");
			future2.thenAcceptAsync(new Consumer<String>() {
				@Override
				public void accept(String value) {
					System.out.println("The value is : " + value);

				}
			});

			// Thread.currentThread().sleep(5000);

			/*
			 * count++; if(count == 3) {
			 */

			connection.close();
			redisClient.shutdown();
			redisClient = null;
			count = 0;
			// }

		} catch (Exception e) {
			e.printStackTrace();
			connection.close();
			redisClient.shutdown();
			redisClient = null;
		}
		return result;
	}

	// cassandra db 3.1.1
	private static final Logger LOG = LoggerFactory.getLogger(Application.class);

	public static String cassandradb3(String host) {

		CassandraConnector connector = new CassandraConnector();
		connector.connect(host, null);

		Session session = connector.getSession();

		KeyspaceRepository sr = new KeyspaceRepository(session);
		sr.createKeyspace("library", "SimpleStrategy", 1);
		sr.useKeyspace("library");

		BookRepository br = new BookRepository(session);
		br.createTable();
		br.alterTablebooks("publisher", "text");

		br.createTableBooksByTitle();

		Book book = new Book(UUIDs.timeBased(), "Effective Java", "Joshua Bloch", "Programming");
		br.insertBookBatch(book);

		br.selectAll().forEach(o -> LOG.info("Title in books: " + o.getTitle()));
		br.selectAllBookByTitle().forEach(o -> LOG.info("Title in booksByTitle: " + o.getTitle()));

		br.deletebookByTitle("Effective Java");
		br.deleteTable("books");
		br.deleteTable("booksByTitle");

		sr.deleteKeyspace("library");
		System.out.println("processing done!");
		connector.close();
		return "cassandra call executed";

	}

	// cassandra db 4.0.1
	public static String cassandradb4(String host) {
		/*
		 * To create in keyspace and table in DB --------- CREATE KEYSPACE demo WITH
		 * replication = {'class': 'SimpleStrategy', 'replication_factor': '1'};
		 * 
		 * CREATE TABLE demo.users ( lastname text PRIMARY KEY, age int, city text,
		 * email text, firstname text);
		 */

		try (CqlSession session = CqlSession.builder().addContactPoint(new InetSocketAddress("127.0.0.1", 9042))
				.withKeyspace("demo").withLocalDatacenter("datacenter1").build()) {

			setUser(session, "Jones", 35, "Austin", "bob@example.com", "Bob");

			getUser(session, "Jones");

			updateUser(session, 36, "Jones");

			getUser(session, "Jones");

			deleteUser(session, "Jones");

		}
		return "db call done !";
	}

	private static void setUser(CqlSession session, String lastname, int age, String city, String email,
			String firstname) {
		System.out.println("set user");
		// TO DO: execute SimpleStatement that inserts one user into the table
		session.execute(
				SimpleStatement.builder("INSERT INTO users (lastname, age, city, email, firstname) VALUES (?,?,?,?,?)")
						.addPositionalValues(lastname, age, city, email, firstname).build());
	}

	private static void getUser(CqlSession session, String lastname) {

		// TO DO: execute SimpleStatement that retrieves one user from the table
		// TO DO: print firstname and age of user
		com.datastax.oss.driver.api.core.cql.ResultSet rs = session.execute(
				SimpleStatement.builder("SELECT * FROM users WHERE lastname=?").addPositionalValue(lastname).build());
		System.out.println("get user");
		Row row = rs.one();
		System.out.format("%s %d\n", row.getString("firstname"), row.getInt("age"));
	}

	private static void updateUser(CqlSession session, int age, String lastname) {

		// TO DO: execute SimpleStatement that updates the age of one user
		System.out.println("update user");
		session.execute(SimpleStatement.builder("UPDATE users SET age =?  WHERE lastname =? ")
				.addPositionalValues(age, lastname).build());
	}

	private static void deleteUser(CqlSession session, String lastname) {
		System.out.println("delete user");
		// TO DO: execute SimpleStatement that deletes one user from the table
		session.execute(
				SimpleStatement.builder("DELETE FROM users WHERE lastname=?").addPositionalValue(lastname).build());

	}

	// MONGO DB --->
	public static String Insert(String server, String database, String collection) {
		// TODO Auto-generated method stub

		MongoClient mongoClient = new MongoClient(server, 27017);

		System.out.println("Connected to MongoDB!");
		System.out.println("insert");
		@SuppressWarnings("deprecation")
		DB db = mongoClient.getDB(database);
		System.out.println(db);
		System.out.println(server);
		DBCollection coll = db.getCollection(collection);
		System.out.println(coll);
		System.out.println("Collection mycol selected successfully");

		// insert a document
		// creating object with different key/value pairs
		BasicDBObject doc = new BasicDBObject("title", "MongoDB").append("description", "database").append("likes", 100)
				.append("url", "http://10.10.40.12:7001/nsecom").append("by", "tutorials point");

		BasicDBObject doc1 = new BasicDBObject("title", "MongoDB").append("description", "db").append("likes", 200)
				.append("url", "http://10.10.40.12:7001/nsecom").append("by", "tutorialsT");

		BasicDBObject doc2 = new BasicDBObject("title", "MongoDB").append("description", "db1").append("likes", 300)
				.append("url", "http://10.10.40.12:7001/nsecom").append("by", "tutorial");

		BasicDBObject doc3 = new BasicDBObject("title", "MongoDB").append("description", "db2").append("likes", 400)
				.append("url", "http://10.10.40.12:7001/nsecom").append("by", "tutorsT");

		java.util.List<BasicDBObject> lst = new ArrayList<BasicDBObject>();
		lst.add(doc);
		lst.add(doc1);
		lst.add(doc2);
		lst.add(doc3); // adding objects to the list created
		coll.insert(lst); // insert the list
		System.out.println("Document Inserted Successfully");
		// msg.append("<br>Document Inserted Successfully");
		DBCursor cursor = coll.find();
		cursor = coll.find();
		int i = 1;
		while (cursor.hasNext()) {
			System.out.println("Inserted Document: " + i);
			System.out.println(cursor.next());
			i++;
		}
		mongoClient.close();
		return "Data Inserted Successfully";

	}

	public static String Update(String server, String database, String collection) {
		// TODO Auto-generated method stub

		MongoClient mongoClient = new MongoClient(server, 27017);
		System.out.println("in update");
		System.out.println("Connected to MongoDB!");
		System.out.println("update");
		@SuppressWarnings("deprecation")
		DB db = mongoClient.getDB(database);
		System.out.println(db);
		DBCollection coll = db.getCollection(collection);
		System.out.println(coll);
		System.out.println("Collection mycol selected successfully");

		DBCursor cursor = coll.find();

		BasicDBObject newDocument = new BasicDBObject();
		newDocument.append("$set", new BasicDBObject().append("likes", 900));

		BasicDBObject searchQuery = new BasicDBObject().append("title", "MongoDB");

		coll.updateMulti(searchQuery, newDocument);

		System.out.println("Document Updated Successfully");
		// msg.append("");
		// msg.append("<br>Document Updated Successfully");

		// retrieve all document
		cursor = coll.find();
		int i = 1;
		while (cursor.hasNext()) {
			System.out.println("Updated Document: " + i);
			System.out.println(cursor.next());
			i++;
		}
		mongoClient.close();
		return "Updated and Retrieved Successfully";

	}

	public static String DeleteFirst(String server, String database, String collection) {
		// TODO Auto-generated method stub
		MongoClient mongoClient = new MongoClient(server, 27017);

		System.out.println("Connected to MongoDB!");
		System.out.println("delfirst");
		@SuppressWarnings("deprecation")
		DB db = mongoClient.getDB(database);
		System.out.println(db);
		DBCollection coll = db.getCollection(collection);
		System.out.println(coll);
		System.out.println("Collection mycol selected successfully");
		DBCursor cursor = coll.find();
		DBObject myDoc = coll.findOne();
		coll.remove(myDoc);
		cursor = coll.find();
		System.out.println("First Document Deleted Successfully");
		// msg.append("<br>First Document Deleted Successfully");
		mongoClient.close();
		return "Deleted First Document";
	}

	public static String DeleteAll(String server, String database, String collection) {
		// TODO Auto-generated method stub
		MongoClient mongoClient = new MongoClient(server, 27017);

		System.out.println("Connected to MongoDB!");
		System.out.println("delall");
		@SuppressWarnings("deprecation")
		DB db = mongoClient.getDB(database);
		System.out.println(db);
		DBCollection coll = db.getCollection(collection);
		System.out.println(coll);
		System.out.println("Collection mycol selected successfully");
		DBCursor cursor = coll.find();
		DBObject myDoc = coll.findOne();

		int i = 1;
		while (cursor.hasNext()) {
			myDoc = coll.findOne();
			coll.remove(myDoc);
			System.out.println("Deleted Document: " + i);
			System.out.println(cursor.next());
			i++;
		}
		System.out.println("All Documents Deleted Successfully");

		mongoClient.close();

		return "Deleted All Successfully";
	}

	/*
	 * public static String DeleteAllRobo() { // TODO Auto-generated method stub
	 * String server = "10.10.40.12"; String database = "test"; String collection =
	 * "Demo"; MongoClient mongoClient = new MongoClient(server, 27017);
	 * 
	 * System.out.println("Connected to MongoDB!");
	 * 
	 * @SuppressWarnings("deprecation") DB db = mongoClient.getDB(database);
	 * System.out.println(db); DBCollection coll = db.getCollection(collection);
	 * System.out.println(coll);
	 * System.out.println("Collection mycol selected successfully"); DBCursor cursor
	 * = coll.find(); DBObject myDoc = coll.findOne();
	 * 
	 * int i = 1; while (cursor.hasNext()) { myDoc = coll.findOne();
	 * coll.remove(myDoc); System.out.println("Deleted Document: " + i);
	 * System.out.println(cursor.next()); i++; }
	 * System.out.println("All Documents Deleted Successfully");
	 * 
	 * mongoClient.close();
	 * 
	 * return "Deleted All Successfully"; }
	 */

	public static String GetCount(String server, String database, String collection) {
		// TODO Auto-generated method stub
		MongoClient mongoClient = new MongoClient(server, 27017);

		System.out.println("Connected to MongoDB!");
		System.out.println("getcount");
		@SuppressWarnings("deprecation")
		DB db = mongoClient.getDB(database);
		System.out.println(db);
		DBCollection coll = db.getCollection(collection);
		System.out.println(coll);
		System.out.println("Collection mycol selected successfully");
		BasicDBObject doc2 = new BasicDBObject("title", "MongoDB").append("description", "db1").append("likes", 300)
				.append("url", "http://10.10.40.12:7001/nsecom").append("by", "tutorial");
		// int count1 = (int)coll.find(Filters.eq("value")).count();
		// Bson filter= Filters.eq("title", "MongoDB");
		// long count =coll.count(filter);

		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("title", "MongoDB");
		// long count = coll.find(whereQuery).count();

		long count = coll.count(whereQuery);

		System.out.println("Total count :" + count);

		DBCursor cursor = coll.find();

		int i = 1;
		while (cursor.hasNext()) {
			System.out.println("Count: " + i);
			System.out.println(cursor.next());
			i++;
		}

		mongoClient.close();

		return "Total count :" + count;
	}

	public static String Rename(String server, String database, String collection, String newcollection) {
		MongoClient mongoClient = new MongoClient(server, 27017);
		System.out.println("in rename");
		System.out.println("Connected to MongoDB!");
		System.out.println("rename");
		@SuppressWarnings("deprecation")
		DB db = mongoClient.getDB(database);
		System.out.println(db);
		DBCollection coll = db.getCollection(collection);
		System.out.println(coll);
		System.out.println("Collection mycol selected successfully");
		coll.rename(newcollection);

		System.out.println(coll);

		return "renamed";
	}

	public static String Distinct(String server, String database, String collection) {
		// TODO Auto-generated method stub
		MongoClient mongoClient = new MongoClient(server, 27017);

		System.out.println("Connected to MongoDB!");
		System.out.println("distinct");
		@SuppressWarnings("deprecation")
		DB db = mongoClient.getDB(database);
		System.out.println(db);
		DBCollection coll = db.getCollection(collection);
		System.out.println(coll);
		System.out.println("Collection mycol selected successfully");

		String distinct = coll.distinct("by").toString();
		System.out.println(distinct);
		return distinct;

	}

	@SuppressWarnings("deprecation")
	public static String Group(String server, String database, String collection) {
		// TODO Auto-generated method stub
		MongoClient mongoClient = new MongoClient(server, 27017);

		System.out.println("Connected to MongoDB!");
		System.out.println("group");
		@SuppressWarnings("deprecation")
		DB db = mongoClient.getDB(database);
		System.out.println(db);
		DBCollection coll = db.getCollection(collection);
		System.out.println(coll);
		System.out.println("Collection mycol selected successfully");

		// System.out.println(coll.aggregate(Arrays.asList(new Document("$match", new
		// Document("views.isActive", true)))));

		@SuppressWarnings("deprecation")
		GroupCommand groupCommad = new GroupCommand(coll, new BasicDBObject("title", true), null,
				new BasicDBObject("count", 0), "function(key,val){ val.count++;}", null);

		DBObject obj = coll.group(groupCommad);
		System.out.println(obj);

		return "Group done:" + obj;
	}

	public static String Aggregate(String server, String database, String collection) {
		// TODO Auto-generated method stub

		MongoClient mongoClient = new MongoClient(server, 27017);

		System.out.println("Connected to MongoDB!");
		System.out.println("aggregate");
		@SuppressWarnings("deprecation")
		DB db = mongoClient.getDB(database);
		System.out.println(db);
		DBCollection coll = db.getCollection(collection);
		System.out.println(coll);
		System.out.println("Collection mycol selected successfully");

		final BasicDBObject match = new BasicDBObject();
		match.put("title", "MongoDB");

		final BasicDBObject group = new BasicDBObject();
		group.put("_id", 0);
		group.put("count", new BasicDBObject("$sum", 1));

		final BasicDBObject matchOp = new BasicDBObject("$match", match);
		final BasicDBObject groupOp = new BasicDBObject("$group", group);

		final AggregationOutput result = coll.aggregate(matchOp, groupOp);
		String aggr = null;
		System.out.println("getNumOfReplyCodeByRequestType: ");
		for (DBObject obj : result.results()) {
			System.out.println(obj.get("count"));
			aggr = obj.get("count").toString();
		}

		return "Aggregation Done: getNumOfReplyCodeByRequestType=" + aggr;
	}

	public static String MapReduce(String server, String database, String collection) {
		// TODO Auto-generated method stub
		MongoClient mongoClient = new MongoClient(server, 27017);

		System.out.println("Connected to MongoDB!");
		System.out.println("mapreduce");
		@SuppressWarnings("deprecation")
		DB db = mongoClient.getDB(database);
		System.out.println(db);
		DBCollection coll = db.getCollection(collection);
		System.out.println(coll);
		System.out.println("Collection mycol selected successfully");

		DBObject o1 = new BasicDBObject("name", "Rhys Lee").append("age", 29).append("sex", 1).append("type", 6);

		DBObject o2 = new BasicDBObject("name", "Ari").append("age", 23).append("sex", 0).append("type", 3);

		DBObject o3 = new BasicDBObject("name", "Atrox").append("age", 30).append("sex", 1).append("type", 5);

		DBObject o4 = new BasicDBObject("name", "Atrox1").append("age", 35).append("sex", 1).append("type", 5);

		DBObject o5 = new BasicDBObject("name", "Atrox2").append("age", 25).append("sex", 1).append("type", 5);

		DBCollection c = db.getCollection("demo2");
		c.drop();
		c.insert(Arrays.asList(o1, o2, o3, o4, o5));

		String map = "function() { " + "var category; " + "if ( this.age >= 30 ) " + "category = 'Big'; " + "else "
				+ "category = 'Small'; " + "emit(category, {name: this.name});}";

		String reduce = "function(key, values) { " + "var sum = 0; " + "values.forEach(function(doc) { sum += 1; "
				+ "}); " + "return {cnt : sum};} ";

		MapReduceCommand cmd = new MapReduceCommand(c, map, reduce, null, MapReduceCommand.OutputType.INLINE, null);
		MapReduceOutput out = c.mapReduce(cmd);
		System.out.println("Mapreduce results");
		String mapReduce = "";
		for (DBObject o : out.results()) {
			System.out.println(o.toString());
			mapReduce = mapReduce + "," + o.toString();
		}

		return "MapReduce Done :" + mapReduce;
	}

	public static String CreateIndex(String server, String database, String collection) {
		MongoClient mongoClient = new MongoClient(server, 27017);

		System.out.println("Connected to MongoDB!");
		System.out.println("createindex");
		@SuppressWarnings("deprecation")
		DB db = mongoClient.getDB(database);
		System.out.println(db);
		DBCollection coll = db.getCollection(collection);
		System.out.println(coll);
		System.out.println("Collection mycol selected successfully");
		coll.createIndex(new BasicDBObject("title", 1));
		System.out.println(coll.getIndexInfo());
		// String ab =coll.createIndex("title");
		return "CreateIndex Done:" + coll.getIndexInfo();
	}

	public static String FindAndModify(String server, String database, String collection) {

		MongoClient mongoClient = new MongoClient(server, 27017);

		System.out.println("Connected to MongoDB!");
		System.out.println("findmodify");
		@SuppressWarnings("deprecation")
		DB db = mongoClient.getDB(database);
		System.out.println(db);
		DBCollection coll = db.getCollection(collection);
		System.out.println(coll);
		System.out.println("Collection mycol selected successfully");
		coll.drop();
		BasicDBObject obj = new BasicDBObject();
		obj.append("name", "Volkswagen");
		obj.append("color", "JetBlue");
		obj.append("cno", "H672");
		obj.append("speed", 40);
		obj.append("mfdcountry", "Italy");
		coll.insert(obj);

		// findAndModify operation. Update colour to blue for cars having speed
		// < 45
		DBObject query = new BasicDBObject("speed", new BasicDBObject("$lt", 45));
		DBObject update = new BasicDBObject();
		update.put("$set", new BasicDBObject("color", "Blue"));

		DBCursor cursor = coll.find();
		System.out.println("BEFORE MODIFICATION:");

		while (cursor.hasNext()) {
			System.out.println(cursor.next());

		}

		coll.findAndModify(query, update);
		cursor = coll.find();
		System.out.println("AFTER MODIFICATION:");
		while (cursor.hasNext()) {
			System.out.println(cursor.next());
			// System.out.println("1");
		}

		return "FindAndModify Done";
	}

	public static String Drop(String server, String database, String collection) {
		MongoClient mongoClient = new MongoClient(server, 27017);

		System.out.println("Connected to MongoDB!");
		System.out.println("drop");
		@SuppressWarnings("deprecation")
		DB db = mongoClient.getDB(database);
		System.out.println(db);
		DBCollection coll = db.getCollection(collection);
		System.out.println(coll);
		System.out.println("Collection mycol selected successfully");
		coll.drop();
		return "Drop done";
	}

	public static String DropIndex(String server, String database, String collection) {
		MongoClient mongoClient = new MongoClient(server, 27017);

		System.out.println("Connected to MongoDB!");
		System.out.println("dropindex");
		@SuppressWarnings("deprecation")
		DB db = mongoClient.getDB(database);
		System.out.println(db);
		DBCollection coll = db.getCollection(collection);
		System.out.println(coll);
		System.out.println("Collection mycol selected successfully");
		System.out.println("BEFORE DROP ::" + coll.getIndexInfo());
		coll.dropIndex("title_1");
		System.out.println("AFTER DROP ::" + coll.getIndexInfo());
		return "DropIndex Done :" + coll.getIndexInfo();
	}

	@SuppressWarnings("deprecation")
	public static String ParallelScan(String server, String database, String collection) {
		MongoClient mongoClient = new MongoClient(server, 27017);

		System.out.println("Connected to MongoDB!");
		System.out.println("pscan");
		@SuppressWarnings("deprecation")
		DB db = mongoClient.getDB(database);
		System.out.println(db);
		DBCollection coll = db.getCollection(collection);
		System.out.println(coll);
		System.out.println("Collection mycol selected successfully");
		int numCursors = 10;
		List<Cursor> cursors = coll
				.parallelScan(ParallelScanOptions.builder().numCursors(numCursors).batchSize(1000).build());
		System.out.println(cursors);
		String parallelScan = cursors.toString();
		return "Parallel Scan Done :" + parallelScan;
	}

}
