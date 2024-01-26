package com.cavisson.ata.webservice.soap;

import java.net.URL;

import javax.xml.ws.Endpoint;

import com.cavisson.ata.webservice.soap.*;

public class WebServiceServer {

	/**
	 * Starts a simple server to deploy the web service. 
	 * @return 
	 */
	public static String Response() {
		//String bindingURI="http://localhost:7779/ws/hello?wsdl";  

		String bindingURI = "http://localhost:9898/md5WebService";
	  MD5WebService webService = new MD5WebService();
		Endpoint.publish(bindingURI, webService);
		System.out.println("Server started at: " + bindingURI);
		return "Response method called!!";
	}
}
