package com.dmitryprod.botmessageconsumer.logging.config;

import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.appender.db.jdbc.AbstractConnectionSource;
import org.apache.logging.log4j.core.appender.db.jdbc.ColumnConfig;
import org.apache.logging.log4j.core.appender.db.jdbc.JdbcAppender;
import org.apache.logging.log4j.core.async.AsyncLoggerContext;
import org.apache.logging.log4j.core.filter.ThresholdFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LoggingJdbcConfig {

    AbstractConnectionSource connectionSource;

    @Value("${LOGGED_APPLICATION_NAME}")
    String applicationName;

    @PostConstruct
    public void init() {
        AsyncLoggerContext loggerContext = (AsyncLoggerContext) LogManager.getContext(false);
        var config = loggerContext.getConfiguration();
        JdbcAppender jdbcAppender = createJdbcAppender(createColumnConfigs());

        jdbcAppender.start();
        ((Logger) LogManager.getRootLogger()).addAppender(jdbcAppender);
        loggerContext.updateLoggers();
    }

    private ColumnConfig[] createColumnConfigs() {
        return new ColumnConfig[]{
                ColumnConfig.newBuilder().setName("date").setEventTimestamp(true).build(),
                ColumnConfig.newBuilder().setName("level").setPattern("%p").setUnicode(false).build(),
                ColumnConfig.newBuilder().setName("pid").setPattern("%pid").setUnicode(false).build(),
                ColumnConfig.newBuilder().setName("app_name").setPattern(applicationName).setUnicode(false).build(),
                ColumnConfig.newBuilder().setName("thread_name").setPattern("%t").setUnicode(false).build(),
                ColumnConfig.newBuilder().setName("class_name").setPattern("%c{1.}").setUnicode(false).build(),
                ColumnConfig.newBuilder().setName("msg").setPattern("%m").setUnicode(false).build(),
                ColumnConfig.newBuilder().setName("thrown").setPattern("%ex{short}").setUnicode(false).build(),
                ColumnConfig.newBuilder().setName("cause").setPattern("%rEx{short}").setUnicode(false).build()
        };
    }

    private JdbcAppender createJdbcAppender(ColumnConfig[] columnConfigs) {
        ThresholdFilter filter = ThresholdFilter.createFilter(Level.INFO, null, null);
        return JdbcAppender.newBuilder()
                .setName("DatabaseAppender")
                .setColumnConfigs(columnConfigs)
                .setConnectionSource(connectionSource)
                .setTableName("log_info")
                .setFilter(filter)
                .setIgnoreExceptions(true)
                .build();
    }
}
