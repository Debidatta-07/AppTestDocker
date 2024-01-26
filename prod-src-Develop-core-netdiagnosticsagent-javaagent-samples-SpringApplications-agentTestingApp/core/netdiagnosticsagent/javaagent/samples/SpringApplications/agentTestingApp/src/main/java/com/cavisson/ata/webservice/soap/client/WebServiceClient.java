package com.cavisson.ata.webservice.soap.client;

import com.cavisson.ata.webservice.soap.*;


public class WebServiceClient {

	/**
	 * Starts the web service client.
	 */
	public static void Request() {
		MD5WebServiceService client = new MD5WebServiceService();
		MD5WebService md5Webservice = client.getMD5WebServicePort();
		String hash = md5Webservice.hashString("admin");
		System.out.println("MD5 hash string: " + hash);
	}
}