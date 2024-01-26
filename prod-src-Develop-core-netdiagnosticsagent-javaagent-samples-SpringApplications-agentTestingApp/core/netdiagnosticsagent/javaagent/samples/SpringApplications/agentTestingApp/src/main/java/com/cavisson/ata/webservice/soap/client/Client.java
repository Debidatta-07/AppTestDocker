package com.cavisson.ata.webservice.soap.client;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.cavisson.ata.webservice.soap.*;

public class Client {

	public static String Request() throws Exception {
		// URL url = new URL("http://localhost:7779/ws/hello?wsdl");
		URL url = new URL("http://localhost:9898/md5WebService?wsdl");
		// 1st argument service URI, refer to wsdl document above
		// 2nd argument is service name, refer to wsdl document above
		QName qname = new QName("http://soap.webservice.ata.cavisson.com/", "MD5WebServiceService");
		// QName qname = new QName("http://soap.webservice.cavisson.com/",
		// "ServerImplService");
		Service service = Service.create(url, qname);
		Server obj = service.getPort(Server.class);
		System.out.println(obj.getMessageAsString("cavisson"));
		return "Request method called of Client Class!!";

	}

}
