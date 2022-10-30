package topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import topic.util.MqUtil;

import java.io.IOException;

public class Provider {
    public static void main(String[] args) throws IOException {
        Connection connection = MqUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("ex", "topic");
        channel.basicPublish("ex", "user.all", null, "for everyone".getBytes());
        System.out.println("发送面向所有人消息");
        channel.basicPublish("ex", "user.male", null, "for male".getBytes());
        System.out.println("发送面向男性消息");
        channel.basicPublish("ex", "user.female", null, "for female".getBytes());
        System.out.println("发送面向女性消息");
        MqUtil.closeChannelAndConnection(channel, connection);
    }
}
