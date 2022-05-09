package ge.anita.rabbitmqexchangedemo;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @RabbitListener(queues = ExchangeConfig.FANOUT_QUEUE_1_NAME)
    public void consumeMessageFromFanoutQueue1(String message) {
        System.out.println(
                "Fanout Queue 1 --> Received Message: " + message
        );
    }

    @RabbitListener(queues = ExchangeConfig.FANOUT_QUEUE_2_NAME)
    public void consumeMessageFromFanoutQueue2(String message) {
        System.out.println(
                "Fanout Queue 2 --> Received Message: " + message
        );
    }

    @RabbitListener(queues = ExchangeConfig.TOPIC_QUEUE_1_NAME)
    public void consumeMessageFromTopicQueue1(String message) {
        System.out.println(
                "Topic Queue 1 --> Topic: " + Producer.BINDING_PATTERN_LEGAL +
                        ", Received Message: " + message
        );
    }

    @RabbitListener(queues = ExchangeConfig.TOPIC_QUEUE_2_NAME)
    public void consumeMessageFromTopicQueue2(String message) {
        System.out.println(
                "Topic Queue 2 --> Topic: " + Producer.BINDING_PATTERN_ERROR +
                        ", Received Message: " + message
        );
    }

}
