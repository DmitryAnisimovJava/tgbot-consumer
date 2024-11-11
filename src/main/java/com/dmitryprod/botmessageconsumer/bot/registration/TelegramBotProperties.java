package com.dmitryprod.botmessageconsumer.bot.registration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "telegram.bot")
@Validated
public record TelegramBotProperties(

        boolean needRegistration
) {
}

