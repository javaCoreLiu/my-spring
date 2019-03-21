package com.bootstrap.mq.single;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

/**
 * @类描述：消费者
 * @创建人：liulei
 * @创建时间：2019年3月21日 下午2:54:35
 * @版权：Copyright (c)
 */
public class Customer {
    
    // 队列名字
    public final static String queue_name = "rabbitmq_test";
    
    public static void main(String[] args) throws IOException, TimeoutException {
        // 连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        // 创建新连接
        Connection connection = factory.newConnection();
        // 创建连接通道
        Channel channel = connection.createChannel();
        // 声明要关注的队列
        channel.queueDeclare(queue_name, false, false, false, null);
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body) throws IOException {
                super.handleDelivery(consumerTag, envelope, properties, body);
                String message = new String(body, "UTF-8");
                System.out.println("接受到消息:" + message);
            }
        };
        channel.basicConsume(queue_name, true, consumer);
    }
    
}
