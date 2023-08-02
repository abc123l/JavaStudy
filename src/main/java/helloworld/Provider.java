package helloworld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import org.junit.Test;
import utils.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author abc
 * @version 1.0
 */
public class Provider {
    private int p;
    private int id;
    @Test
    public void sendMessage() throws IOException, TimeoutException {
//        ConnectionFactory connectionFactory = new ConnectionFactory();
//        connectionFactory.setHost("192.168.208.132");
//        connectionFactory.setPort(5672);
//        connectionFactory.setVirtualHost("/ems");
//        connectionFactory.setUsername("ems");
//        connectionFactory.setPassword("123");
        //获取连接对象
        Connection connection = RabbitMQUtils.getConnection();
        //通过连接获取通道
        Channel channel = connection.createChannel();
        //通过通道创建队列
        /**
         * 是否持久化 如果不的话 重启队列删除 持久化的话，消息要额外设置否则只把队列持久化了
         * 是否独占队列 如果为true只有当前一个通道可以绑定
         * 是否消费完成后删除队列
         * 附加参数
         */
        channel.queueDeclare("aa",true,false,true,null);
        //将通道与队列绑定并发送消息
        /**
         * 交换机名称
         * 队列名称
         * 额外设置，涉及到是不是要将消息持久化
         * 消息体
         */
        channel.basicPublish("","aa", MessageProperties.PERSISTENT_TEXT_PLAIN,"hello rabbitmq".getBytes());
//        channel.close();
//        connection.close();
        RabbitMQUtils.close(channel,connection);
    }
    @Test
    public void test(){
        System.out.printf(":");
    }
}
