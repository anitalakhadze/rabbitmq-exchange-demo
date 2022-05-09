package ge.anita.rabbitmqexchangedemo;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Producer {
    public static final String BINDING_PATTERN_LEGAL = "company.legal.documents";
    public static final String BINDING_PATTERN_ERROR = "company.documents.report.error";

    @Bean
    public ApplicationRunner runner(RabbitTemplate rabbitTemplate) {
        String message = "A sample message";
        return args -> {
            rabbitTemplate.convertAndSend(
                    ExchangeConfig.FANOUT_EXCHANGE_NAME,
                    "",
                    message
            );
            rabbitTemplate.convertAndSend(
                    ExchangeConfig.TOPIC_EXCHANGE_NAME,
                    BINDING_PATTERN_LEGAL,
                    message
            );
            rabbitTemplate.convertAndSend(
                    ExchangeConfig.TOPIC_EXCHANGE_NAME,
                    BINDING_PATTERN_ERROR,
                    message
            );
        };
    }
}
