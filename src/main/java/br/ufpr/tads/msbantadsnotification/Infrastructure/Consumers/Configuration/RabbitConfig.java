package br.ufpr.tads.msbantadsnotification.Infrastructure.Consumers.Configuration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.ufpr.tads.msbantadsnotification.Application.Services.Client.Events.ClientApprovedEvent;
import br.ufpr.tads.msbantadsnotification.Application.Services.Client.Events.ClientRejectedEvent;
import br.ufpr.tads.msbantadsnotification.Application.Services.Client.Events.CreateClientFailEvent;
import br.ufpr.tads.msbantadsnotification.Application.Services.Manager.Events.CreatedManagerEvent;

@Configuration
public class RabbitConfig {
    @Value("${notification.queue}")
    private String notificationQueue;

    @Bean
    public Queue queue() {
        return new Queue(notificationQueue, true);
    }

    @Bean
    public MessageConverter messageConverter() {
        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
        converter.setClassMapper(classMapper());
        return converter;
    }

    @Bean
    public DefaultClassMapper classMapper() {
        DefaultClassMapper classMapper = new DefaultClassMapper();
        classMapper.setIdClassMapping(customClassMapping());
        return classMapper;
    }

    public Map<String, Class<?>> customClassMapping(){
        Map<String, Class<?>> idClassMapping = new HashMap<>();
        idClassMapping.put("ClientApprovedEvent", ClientApprovedEvent.class);
        idClassMapping.put("CreatedManagerEvent", CreatedManagerEvent.class);
        idClassMapping.put("ClientRejectedEvent", ClientRejectedEvent.class);
        idClassMapping.put("CreateClientFailEvent", CreateClientFailEvent.class);
        
        return idClassMapping;
    }


    public AmqpTemplate template(ConnectionFactory connection) {
        RabbitTemplate template = new RabbitTemplate(connection);
        template.setMessageConverter(messageConverter());
        return template;
    }
}
