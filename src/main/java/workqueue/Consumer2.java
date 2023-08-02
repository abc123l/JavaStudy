package workqueue;

import com.rabbitmq.client.*;
import utils.RabbitMQUtils;

import java.io.IOException;

/**
 * @author abc
 * @version 1.0
 */
public class Consumer2 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        final Channel channel = connection.createChannel();
        /**
         * 是否持久化
         * 是否独占队列
         * 是否消费完成后删除队列
         * 附加参数
         */
        channel.queueDeclare("worker",true,false,false,null);
        channel.basicQos(1);
        /**
         * 消息自动确认机制是否开启
         * 消费时回调接口
         */
        channel.basicConsume("worker",false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者2消费"+new String(body));
                //手动确认
                /**
                 * 手动确认消息标识 确认队列中的哪个消息
                 * false每次确认一个
                 */
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });
    }
}
