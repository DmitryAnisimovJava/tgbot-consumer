package com.dmitryprod.botmessageconsumer;

import com.dmitryprod.botmessageconsumer.bot.config.TelegramBotProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.sql.Connection;

@SpringBootApplication
@EnableConfigurationProperties(TelegramBotProperties.class)
public class BotMessageConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BotMessageConsumerApplication.class, args);
    }
}
