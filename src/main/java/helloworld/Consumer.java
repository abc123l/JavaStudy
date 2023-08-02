package helloworld;

import com.rabbitmq.client.*;
import org.junit.Test;
import utils.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author abc
 * @version 1.0
 */
public class Consumer {
    public static void main(String[] args) throws IOException, TimeoutException {
//        ConnectionFactory connectionFactory = new ConnectionFactory();
//        connectionFactory.setHost("192.168.208.132");
//        connectionFactory.setPort(5672);
//        connectionFactory.setVirtualHost("/ems");
//        connectionFactory.setUsername("ems");
//        connectionFactory.setPassword("123");
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        /**
         * 是否持久化
         * 是否独占队列
         * 是否消费完成后删除队列
         * 附加参数
         */
        channel.queueDeclare("aa",true,false,true,null);

        /**
         * 消息自动确认机制是否开启
         * 消费时回调接口
         */
        channel.basicConsume("aa",true, new DefaultConsumer(channel){
            @Override//body是消息队列中取出的消息
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("body="+new String(body));
            }
        });
//        channel.close();如果立即关闭的话可能来不及执行回调函数
//        connection.close();
    }

}
