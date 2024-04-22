package com.soaptojson.restsoap.Convert;

import org.springframework.stereotype.Component;

@Component
public class XmlRequestFormat {

    public String xmlparser(String id)
    {
        String xmlRequeString = "<soapenv:Envelope xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/' xmlns:ser='http://superdev.io/student-service'>"
        +"<soapenv:Header/>"+
        "<soapenv:Body>"+
           "<ser:getStudentRequest>"+
              "<ser:id>"+ id +"</ser:id>"+
          " </ser:getStudentRequest>"+
       "</soapenv:Body>"+
     "</soapenv:Envelope>";
     return xmlRequeString;
    }
}
