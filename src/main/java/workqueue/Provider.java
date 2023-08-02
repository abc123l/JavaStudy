package workqueue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import utils.RabbitMQUtils;

import java.io.IOException;

/**
 * @author abc
 * @version 1.0
 */
public class Provider {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        /**
         * 是否持久化 如果不的话 重启队列删除 持久化的话，消息要额外设置否则只把队列持久化了
         * 是否独占队列 如果为true只有当前一个通道可以绑定
         * 是否消费完成后删除队列
         * 附加参数
         */
        channel.queueDeclare("worker",true,false,false,null);

        //生产消息
        /**
         * 交换机名称
         * 队列名称
         * 额外设置，涉及到是不是要将消息持久化
         * 消息体
         */
        for (int i = 0; i < 20; i++) {
            channel.basicPublish("","worker", null, ("work queue"+i).getBytes());
        }


        RabbitMQUtils.close(channel,connection);

    }
}
