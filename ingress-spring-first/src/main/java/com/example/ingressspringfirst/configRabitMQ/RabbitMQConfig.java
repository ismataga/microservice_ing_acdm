package com.example.ingressspringfirst.configRabitMQ;

import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    private final String usersQ;
    private final String usersDLQ;
    private final String usersQExchange;
    private final String usersDLQExchange;
    private final String usersQKey;
    private final String usersDLQKey;

    public RabbitMQConfig(@Value("${rabbitmq.users.queue}")
                          String usersQ,
                          @Value("${rabbitmq.users.dql}")
                          String usersDLQ) {
        this.usersQ = usersQ;
        this.usersDLQ = usersDLQ;
        this.usersQExchange = usersQ + "_Exchange";
        this.usersDLQExchange = usersDLQ + "_Exchange";
        this.usersQKey = usersQ + "_Key";
        this.usersDLQKey = usersDLQ + "_Key";

    }

    @Bean
    DirectExchange usersDLQExchange() {
        return new DirectExchange(usersDLQExchange);
    }

    @Bean
    DirectExchange usersQExchange() {return new DirectExchange(usersQExchange);}
    @Bean
    Queue usersDLQ() {
        return  QueueBuilder.durable(usersDLQ).build();
    }

    @Bean
    Queue usersQ() {
        return  QueueBuilder.durable(usersQ)
                .withArgument("x-dead-letter-exchange",usersDLQExchange)
                .withArgument("x-dead-letter-routing-key",usersDLQKey)
                .build();
    }

    @Bean
    Binding usersDLQBinding() {
        return BindingBuilder.bind(usersDLQ())
                .to(usersDLQExchange()).with(usersDLQKey);
    }

    @Bean
    Binding usersQBinding() {
        return BindingBuilder.bind(usersQ())
                .to(usersQExchange()).with(usersQKey);
    }


}
