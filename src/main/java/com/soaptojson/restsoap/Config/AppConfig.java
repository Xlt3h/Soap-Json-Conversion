package com.soaptojson.restsoap.Config;

import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.ProducerTemplate;

import com.soaptojson.restsoap.Convert.JsonProcess;
import com.soaptojson.restsoap.Convert.XmlProcessor;
import com.soaptojson.restsoap.Routes.SendToSoap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig 
{
    @Autowired
    private CamelContext camelContext;
    
    @Bean
    public ProducerTemplate producerTemplate()
    {
        return camelContext.createProducerTemplate();
    }

    @Bean
    public ConsumerTemplate consumerTemplate()
    {
        return camelContext.createConsumerTemplate();
    }

    
    @Bean
    public XmlProcessor xmlProcessor()
    {
        return new XmlProcessor();
    }
    
    @Bean
    public JsonProcess jsonProcess()
    {
        return new JsonProcess();
    }

    @Bean 
    public RestTemplate restTemplate()
    {
        return new RestTemplate();
    }
    

    @Bean
    public SendToSoap sendToSoap()
    {
        return new SendToSoap();
    }
}
