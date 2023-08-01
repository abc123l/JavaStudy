package helloworld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author abc
 * @version 1.0
 */
public class Provider {
    private int x;
    @Test
    public void sendMessage() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.208.132");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/ems");
        connectionFactory.setUsername("ems");
        connectionFactory.setPassword("123");
        //获取连接对象
        Connection connection = connectionFactory.newConnection();
        //通过连接获取通道
        Channel channel = connection.createChannel();
        //通过通道创建队列
        /**
         * 是否持久化
         * 是否独占队列
         * 是否消费完成后删除队列
         * 附加参数
         */
        channel.queueDeclare("hello",false,false,false,null);
        //将通道与队列绑定并发送消息
        /**
         * 交换机名称
         * 队列名称
         * 额外设置
         * 消息体
         */
        channel.basicPublish("","hello",null,"hello rabbitmq".getBytes());
        channel.close();
        connection.close();
    }
    @Test
    public void test(){
        System.out.printf(":");
    }
}
