package com.soaptojson.restsoap.Routes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SendToSoap 
{
    @Autowired
    private RestTemplate restTemplate;

    public String sendXmlToSoap(String body)
    {
        String url  ="http://localhost:8080/ws";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "text/xml");
        HttpEntity<String> entity = new HttpEntity<>(body , headers);

        ResponseEntity<String> response = restTemplate.
        exchange(
                    url, //url 
                    HttpMethod.POST, // method post
                    entity, // send this entity
                    String.class // response type must be a string
                );
        
        return response.getBody(); //return the body
    }
}
