package com.hz.RabbitMQ;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by HZ-PC on 2018/5/20.
 */
@EnableRabbit
@Component
public class RabbitBaseSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeNow = "现在的时间是：" + sdf.format(new Date());
        System.out.println("发送消息：" + timeNow);
        rabbitTemplate.convertAndSend(timeNow);
    }
    public void sendMap() {
        Map<String, String> map = new HashMap<>();
        map.put("a", "Apple");
        map.put("b", "Banana");
        System.out.println("发送消息：" + map);
        rabbitTemplate.convertAndSend("amq.topic", "topic.hz2", map);
    }

    public void sendTopic1() {
        String context = "hi, i am message 1";
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("hz_exchange_topic", "topic.message.key", context);
    }

    public void sendTopic2() {
        String context = "hi, i am messages 2";
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("hz_exchange_topic", "topic.messages.key", context);
    }

    /**
     * 给fanout类型交换机发消息，给该交换机所有的绑定队列发消息，路由建设置也白设置
     */
    public void sendFanout() {
        String context = "全局发送";
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("hz_exchange_fanout", "", context);
    }

}
