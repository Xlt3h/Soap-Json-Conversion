package com.soaptojson.restsoap.Convert;

import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class JsonProcess implements Processor 
{
    @Override
    public void process(Exchange exchange)
    {
        @SuppressWarnings("unchecked")
        Map<String,Object> bodyMap = exchange.getIn().getBody(Map.class);
        String id = (String) bodyMap.get("id");
        exchange.getIn().setHeader("id", id);
    }
}
