package com.soaptojson.restsoap.Convert;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JsonProcessorMapper implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception 
    {
        String json = exchange.getIn().getBody(String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(json);
        String id = jsonNode.get("id").asText();
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        exchange.getIn().setBody(map);
    }
}
