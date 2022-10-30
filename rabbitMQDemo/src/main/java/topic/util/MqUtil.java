package topic.util;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MqUtil {
    private static ConnectionFactory factory;

    static {
        factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        factory.setVirtualHost("/ems");
        factory.setUsername("ems");
        factory.setPassword("123");
    }

    public static Connection getConnection() {
        try {
            return factory.newConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }
    }

    public static void closeChannelAndConnection(Channel channel,Connection connection) {
        try {
            if (channel != null) {
                channel.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Channel getChannel() {
        Channel channel;
        try {
            channel = getConnection().createChannel();
            channel.basicQos(1);
            channel.queueDeclare("work", true, false, false, null);
            return channel;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
