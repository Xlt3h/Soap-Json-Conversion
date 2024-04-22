package com.soaptojson.restsoap.Routes;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.soaptojson.restsoap.Convert.JsonProcess;
import com.soaptojson.restsoap.Convert.XmlProcessor;

//@Component
public class Routers  extends RouteBuilder
{
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Override
    public void configure() throws Exception
    {
        restConfiguration().component("servlet").port(8087); // localhost:8080

        rest("/api")
        .post("/xml")
        .consumes("text/xml")
        .to("direct:xmlIn"); //localhost:8080/camel/api/xml

        
        from("direct:xmlIn")
        .log("${body}")
        .convertBodyTo(String.class)
        .bean(SendToSoap.class,"sendXmlToSoap")
        .log("this is the response ${body}")
        .process(new XmlProcessor())
        .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
        .setBody(simple("${body}"))
        .log("soap to json ${body}")
        .end();

        rest("/api")
        .post("/json")
        .consumes("application/json")
        .to("direct:jsonIn"); //localhost:8080/camel/api/xml

        from("direct:jsonIn")
        .process(new Processor() {
            public void process(Exchange exchange) throws Exception {
                String json = exchange.getIn().getBody(String.class);
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(json);
                String id = jsonNode.get("id").asText();
                Map<String, String> map = new HashMap<>();
                map.put("id", id);
                exchange.getIn().setBody(map);
            }
        })
        .log("${body}")
        .setHeader(Exchange.HTTP_METHOD,constant("POST"))
        .setHeader(Exchange.CONTENT_TYPE,constant("text/xml; charset=UTF-8"))
        .process(new JsonProcess())
        .setBody().simple("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://superdev.io/student-service\">"
        + "<soapenv:Header/>"
        + "<soapenv:Body>"
        + "   <ser:getStudentRequest>"
        + "      <ser:id>${header.id}</ser:id>"
        + "   </ser:getStudentRequest>"
        + "</soapenv:Body>"
        + "</soapenv:Envelope>")
        .log("${body}")
        .process(new Processor() {
            public void process(Exchange exchange) throws Exception 
            {
                String json = exchange.getIn().getBody(String.class);
                HttpHeaders headers = new HttpHeaders();
                headers.set("Content-Type", "text/xml");
                HttpEntity<String> entity = new HttpEntity<>(json, headers);
                ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/ws",HttpMethod.POST,entity,String.class);
                exchange.getIn().setBody(response.getBody());
            }
        })
        .end();
        

        
    }
    
}
