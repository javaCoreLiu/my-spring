package com.bootstrap.mq.task;

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
 * @类描述：消费者1
 * @创建人：liulei
 * @创建时间：2019年3月21日 下午3:30:57
 * @版权：Copyright (c)
 */
public class Worker2 {
    // 队列名字
    public final static String queue_name = "rabbitmq_task";
    
    public static void main(String[] args) throws IOException, TimeoutException {
        // 连接工厂
        final ConnectionFactory factory = new ConnectionFactory();
        // 创建新连接
        Connection connection = factory.newConnection();
        // 创建连接通道
        final Channel channel = connection.createChannel();
        // 声明要关注的队列
        channel.queueDeclare(queue_name, true, false, false, null);
        // 设置每次从队列获取数量
        channel.basicQos(1);
        
        final Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body) throws IOException {
                super.handleDelivery(consumerTag, envelope, properties, body);
                String message = new String(body, "UTF-8");
                System.out.println("Work2  Received '" + message + "'");
                channel.basicAck(envelope.getDeliveryTag(), false);
                dowork();
            }
        };
        
        boolean autoAck = false;
        // 消费完确认
        channel.basicConsume(queue_name, autoAck, consumer);
    }
    
    private static void dowork() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
