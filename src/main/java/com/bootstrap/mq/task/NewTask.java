package com.bootstrap.mq.task;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

/**
 * @类描述：生产者,多任务，多消费者处理
 * @创建人：liulei
 * @创建时间：2019年3月21日 下午3:24:11
 * @版权：Copyright (c)
 */
public class NewTask {
    
    // 队列名字
    public final static String queue_name = "rabbitmq_task";
    
    public static void main(String[] args) throws IOException, TimeoutException {
        // 连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        // 创建新连接
        Connection connection = factory.newConnection();
        // 创建连接通道
        Channel channel = connection.createChannel();
        // 声明要关注的队列
        channel.queueDeclare(queue_name, true, false, false, null);
        // 分发信息
        for (int i = 0; i < 10; i++) {
            String message = "这是消息-" + i;
            channel.basicPublish("", queue_name, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes("UTF-8"));
        }
        channel.close();
        connection.close();
    }
}
