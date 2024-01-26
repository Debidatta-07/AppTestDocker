package com.cavisson.ata.webservice.soap;

import javax.xml.ws.Endpoint;

public class ServerPublisher {

	public static void Response() {

		Endpoint.publish("http://localhost:7779/ws/hello", new ServerImpl());

	}

}
