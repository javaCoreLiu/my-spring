package com.bootstrap.mq.single;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @类描述：
 * @创建人：liulei
 * @创建时间：2019年3月21日 下午2:38:46
 * @版权：Copyright (c)
 */
public class Producer {
    
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
        // 创建消息
        String messae = "这是一个消息";
        // 发送消息到队列中
        channel.basicPublish("", queue_name, null, messae.getBytes("UTF-8"));
        System.out.println("消息发送成功");
        channel.close();
        connection.close();
    }
    
}
