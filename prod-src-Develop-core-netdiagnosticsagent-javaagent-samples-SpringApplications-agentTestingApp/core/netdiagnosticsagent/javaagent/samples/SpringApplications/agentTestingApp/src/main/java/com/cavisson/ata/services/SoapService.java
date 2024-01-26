package com.cavisson.ata.services;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

public class SoapService {

 private static SOAPMessage createSOAPRequest() throws Exception {
   MessageFactory messageFactory = MessageFactory.newInstance();
   SOAPMessage soapMessage = messageFactory.createMessage();

   SOAPPart soapPart = soapMessage.getSOAPPart();

   // SOAP Envelope
   SOAPEnvelope envelope = soapPart.getEnvelope();

   envelope.addNamespaceDeclaration("web", "http://www.webserviceX.NET");

   // SOAP Body
   SOAPBody soapBody = envelope.getBody();
   SOAPElement soapBodyElem = soapBody.addChildElement("GetCitiesByCountry", "web");
   SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("CountryName", "web");
   soapBodyElem1.addTextNode("Brazil");
   MimeHeaders header = soapMessage.getMimeHeaders();
   header.setHeader("SOAPAction", "http://www.webserviceX.NET/GetCitiesByCountry");

   soapMessage.saveChanges();

   // Check the input
   System.out.println("Request SOAP Message = ");
   soapMessage.writeTo(System.out);

   return soapMessage;
}

/**
* Method used to print the SOAP Response
*/
private static void printSOAPResponse(SOAPMessage soapResponse) throws Exception {
   TransformerFactory transformerFactory = TransformerFactory.newInstance();
   Transformer transformer = transformerFactory.newTransformer();
   Source sourceContent = soapResponse.getSOAPPart().getContent();
   System.out.println("\nResponse SOAP Message = ");
   StreamResult result = new StreamResult(System.out);
   transformer.transform(sourceContent, result);
}

/**
* Starting point for the SAAJ - SOAP Client Testing
 * @return 
*/
public static String soapCall() {
   try {
       SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
       SOAPConnection soapConnection = soapConnectionFactory.createConnection();

       String url = "http://www.webservicex.com/globalweather.asmx";

       SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(), url);

       printSOAPResponse(soapResponse);

       soapConnection.close();
   } catch (Exception e) {
       System.err.println("Error occurred while sending SOAP Request to Server");

       e.printStackTrace();
   }
//return null;
return "soap call executed";
}
}