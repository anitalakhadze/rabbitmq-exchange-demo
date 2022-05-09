package ge.anita.rabbitmqexchangedemo;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExchangeConfig {
    public static final String FANOUT_EXCHANGE_NAME = "fanout.exchange";
    public static final String TOPIC_EXCHANGE_NAME = "topic.exchange";

    public static final String FANOUT_QUEUE_1_NAME = "fanout.queue1";
    public static final String FANOUT_QUEUE_2_NAME = "fanout.queue2";

    public static final String TOPIC_QUEUE_1_NAME = "topic.queue1";
    public static final String TOPIC_QUEUE_2_NAME = "topic.queue2";

    @Bean
    public Declarables fanoutBindings() {
        Queue fanoutQueue1 = new Queue(FANOUT_QUEUE_1_NAME, false);
        Queue fanoutQueue2 = new Queue(FANOUT_QUEUE_2_NAME, false);

        FanoutExchange fanoutExchange = new FanoutExchange(FANOUT_EXCHANGE_NAME, false, false);

        return new Declarables(
                fanoutQueue1,
                fanoutQueue2,
                fanoutExchange,
                BindingBuilder
                        .bind(fanoutQueue1)
                        .to(fanoutExchange),
                BindingBuilder
                        .bind(fanoutQueue2)
                        .to(fanoutExchange)
        );
    }

    @Bean
    public Declarables topicBindings() {
        Queue topicQueue1 = new Queue(TOPIC_QUEUE_1_NAME, false);
        Queue topicQueue2 = new Queue(TOPIC_QUEUE_2_NAME, false);

        TopicExchange topicExchange = new TopicExchange(TOPIC_EXCHANGE_NAME, false, false);

        return new Declarables(
            topicQueue1,
            topicQueue2,
            topicExchange,
            BindingBuilder
                    .bind(topicQueue1)
                    .to(topicExchange)
                    .with("*.legal.*"),
            BindingBuilder
                    .bind(topicQueue2)
                    .to(topicExchange)
                    .with("#.error")
        );
    }

}