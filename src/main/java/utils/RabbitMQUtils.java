package utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author abc
 * @version 1.0
 */
public class RabbitMQUtils {
    //单例模式，只在类加载的时候创建一次
    private static ConnectionFactory connectionFactory;
    static {
        connectionFactory=new ConnectionFactory();
        connectionFactory.setHost("192.168.208.132");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/ems");
        connectionFactory.setUsername("ems");
        connectionFactory.setPassword("123");
    }
    public static Connection getConnection() {

        try {
            return connectionFactory.newConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close(Channel channel, Connection connection){
        try {
            if (channel!=null) channel.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }
        try {
            if (connection!=null) connection.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
