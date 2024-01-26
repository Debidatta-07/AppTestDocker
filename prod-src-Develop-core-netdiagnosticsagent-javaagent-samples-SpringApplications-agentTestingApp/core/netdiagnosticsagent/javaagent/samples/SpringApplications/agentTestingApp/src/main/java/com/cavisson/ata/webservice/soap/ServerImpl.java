package com.cavisson.ata.webservice.soap;

import javax.jws.WebService;

//Service Implementation
@WebService(endpointInterface = "com.cavisson.ata.webservice.soap.Server")

public class ServerImpl implements Server {

	@Override
	public String getMessageAsString(String msg) {
		return "Soap Service in action: " + msg;
	}
}
