package com.hz.RabbitMQ;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * RabbitMQ消息中间件配置
 * Created by HZ-PC on 2018/5/19.
 */
@Configuration
public class RabbitMQConfig {
    final static String topic_message = "topic.message";
    final static String topic_messages = "topic.messages";

    @Bean
    public Queue queue() {
        return new Queue("hz");
    }
    @Bean
    public Queue queue2() {
        return new Queue("hz2");
    }
    @Bean
    TopicExchange topicExchangeHz() {
        return new TopicExchange("amq.topic");
    }
    @Bean
    Binding bindingExchangeHz(Queue queue, TopicExchange topicExchangeHz) {
        return BindingBuilder.bind(queue).to(topicExchangeHz).with("topic.hz");
    }
    @Bean
    Binding bindingExchangeHz2(Queue queue2, TopicExchange topicExchangeHz) {
        return BindingBuilder.bind(queue2).to(topicExchangeHz).with("topic.hz2");
    }

    /*Topic类型交换机配置Start*/

    /**
     * 队列1
     * @return
     */
    @Bean
    public Queue queueMessage() {
        return new Queue(RabbitMQConfig.topic_message);
    }

    /**
     * 队列2
     * @return
     */
    @Bean
    public Queue queueMessages() {
        return new Queue(RabbitMQConfig.topic_messages);
    }

    /*交换机*/
    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange("hz_exchange_topic");
    }

    /*交换机绑定队列1*/
    @Bean
    Binding bindingExchangeMessage(Queue queueMessage, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueMessage).to(topicExchange).with("topic.message.key");
    }

    /**
     * 交换机绑定队列2,给所有路由建以topic开头的交换机绑定的队列发消息
     * @param queueMessages
     * @param topicExchange
     * @return
     */
    @Bean
    Binding bindingExchangeMessages(Queue queueMessages, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueMessages).to(topicExchange).with("topic.#");
    }
    /*Topic类型交换机配置End*/

    /*Funout类型交换机配置Start*/

    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("hz_exchange_fanout");
    }
    @Bean
    Binding bindingFanoutExchange(Queue queueMessages, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queueMessages).to(fanoutExchange);
    }
    @Bean
    Binding bindingFanoutExchange2(Queue queueMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queueMessage).to(fanoutExchange);
    }
     /*Funout类型交换机配置Start*/
}
