package com.cavisson.ata.webservice.soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.xml.ws.Endpoint;


//Service Endpoint Interface
@WebService
//@SOAPBinding(style = Style.RPC)
@SOAPBinding(style = Style.DOCUMENT)

public interface Server {
	
	@WebMethod String getMessageAsString(String msg);
	 
}


/*@WebService(name = "Server", targetNamespace = "http://ws.mkyong.com/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface Server{

    *//**
     *
     * @param arg0
     * @return
     *     returns java.lang.String
     *//*
    @WebMethod
    @WebResult(partName = "return")
    public String getMessageAsString(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);
}*/