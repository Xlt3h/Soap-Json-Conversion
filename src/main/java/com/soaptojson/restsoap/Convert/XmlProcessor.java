package com.soaptojson.restsoap.Convert;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

@Component
@DependsOn("conversionSoapToJson")
public class XmlProcessor implements Processor 
{
    

    @Override
    public void process(Exchange exchange)
    {
        String soapBody = exchange.getIn().getBody(String.class);
        System.out.println("we are in");
        
        JSONObject xmlJSONObj = XML.toJSONObject(soapBody);
            JSONObject student = xmlJSONObj.getJSONObject("SOAP-ENV:Envelope")
                .getJSONObject("SOAP-ENV:Body")
                .getJSONObject("ns2:getStudentResponse")
                .getJSONObject("ns2:student");
            
        exchange.getMessage().setBody(student);
    }

}
