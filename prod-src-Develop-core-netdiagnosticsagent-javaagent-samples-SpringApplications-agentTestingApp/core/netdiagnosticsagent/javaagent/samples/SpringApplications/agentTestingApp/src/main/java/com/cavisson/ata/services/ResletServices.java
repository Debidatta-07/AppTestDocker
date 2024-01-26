package com.cavisson.ata.services;

import org.restlet.Server;
import org.restlet.data.Protocol;
import org.restlet.resource.ClientResource;

import com.cavisson.ata.utils.RestletResource;

/*
 * A Restlet Server, it starts a server to listen on port
 * and expose RestletResource as RESTful web service.
 * When you run this program, it will listen for HTTP traffic
 * on port, you can access this RESTful web service by connecting
 * your browser to port.
 */
public class ResletServices {

	public static String reserver(String Port) throws Exception {
		int PORT = Integer.parseInt(Port);
		Server server = new Server(Protocol.HTTP, PORT, RestletResource.class);
		server.start();
		return "Restlet server started ---------> Go back and Run Restlet Client";
	}

	public static String resclient(String Port) throws Exception {
		ClientResource client = new ClientResource("http://127.0.0.1:" + Port + "/");
		client.get().write(System.out);
		client.delete().write(System.out);
		return "restlet client executed !";
	}

}
