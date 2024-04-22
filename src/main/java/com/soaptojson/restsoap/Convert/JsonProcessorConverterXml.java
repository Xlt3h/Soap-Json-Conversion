package com.soaptojson.restsoap.Convert;


import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class JsonProcessorConverterXml implements Processor 
{
    @Autowired
    private RestTemplate restTemplate;
    @Override
    public void process(Exchange exchange) throws Exception 
    {
        String json = exchange.getIn().getBody(String.class);
        
         HttpHeaders headers = new HttpHeaders();
         headers.set("Content-Type", "text/xml");
         HttpEntity<String> entity = new HttpEntity<>(json, headers);
         ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/ws",HttpMethod.POST,entity,String.class);
        exchange.getIn().setBody(response.getBody());
    }
    
}
