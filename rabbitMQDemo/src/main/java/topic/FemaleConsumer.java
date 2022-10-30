package topic;

import com.rabbitmq.client.*;
import topic.util.MqUtil;

import java.io.IOException;

public class FemaleConsumer {
    public static void main(String[] args) throws IOException {
        Connection connection = MqUtil.getConnection();
        Channel channel = connection.createChannel();
        String queue = channel.queueDeclare().getQueue();
        channel.queueBind(queue, "ex", "user.female.#");
        channel.queueBind(queue, "ex", "user.all.#");
        System.out.println("女性消费者开始监听...");
        channel.basicConsume(queue, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("女性消费者获取消息：" + new String(body));
            }
        });
    }
}
