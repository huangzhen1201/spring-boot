package com.hz.RabbitMQ;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListeners;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by HZ-PC on 2018/5/20.
 */

@Component
public class RabbitHzReceiver {

    @RabbitListener(queues = "hz")
    @RabbitHandler
    public void process(String meg) {
        System.out.println("接收到消息1：" + meg);
    }


    /**
     * 因为发送的Map类型为发送给两个队列hz,hz2;所以接收者两个队列都要有接受Map类型
     * @param map
     */
    @RabbitListener(queues = "hz2")
    @RabbitHandler
    public void processMap2(Map<String, String> map) {
        System.out.println("接收到消息2map：" + map);
    }

    @RabbitListener(queues = "topic.message")
    @RabbitHandler
    public void process3(String meg) {
        System.out.println("接收到消息3：" + meg);
    }

    @RabbitListener(queues = "topic.messages")
    @RabbitHandler
    public void process4(String meg) {
        System.out.println("接收到消息4：" + meg);
    }
}
