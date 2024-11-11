package com.dmitryprod.botmessageconsumer.bot.registration;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RegistryBotListener {

    TelegramBotProperties botProperties;

    @EventListener(condition = "@'telegram.bot-com.dmitryprod.botmessageconsumer.bot.registration.TelegramBotProperties'.needRegistration()")
    public void registryBot(ContextRefreshedEvent event) {
        log.info("Bot registration started");
    }
}
