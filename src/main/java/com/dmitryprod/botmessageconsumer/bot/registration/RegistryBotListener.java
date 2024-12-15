package com.dmitryprod.botmessageconsumer.bot.registration;

import com.dmitryprod.botmessageconsumer.bot.config.TelegramBotProperties;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RegistryBotListener {

    TelegramBotProperties botProperties;
    Logger log;

    @EventListener(condition = """
                        @'telegram.bot-com.dmitryprod.botmessageconsumer.bot.config.TelegramBotProperties'.needRegistration()
            """)
    public void registryBot(ContextRefreshedEvent event) {
        log.info("Bot registration started");
    }
}
