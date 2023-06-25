package br.com.alura;

import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class NewOrderMain {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        try (var dispatcher = new KafkaDispatcher()) {
            for (int i = 0; i < 100; i++) {
                var key = UUID.randomUUID().toString();

                var value = key + ",213,12312";
                dispatcher.send("ECOMMERCE_NEW_ORDER", key, value);

                var email = "Thank you for your order! We are processing your order!";
                var emailRecord = new ProducerRecord<>("", key, email);
                dispatcher.send("ECOMMERCE_SEND_EMAIL", key, email);
            }
        }
    }

}
