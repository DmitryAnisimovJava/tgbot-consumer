package com.dmitryprod.botmessageconsumer.logging.connection;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.apache.logging.log4j.core.appender.db.jdbc.AbstractConnectionSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@ToString
public class Log4JConnectionSource extends AbstractConnectionSource {

    DataSource dataSource;

    @Override
    public Connection getConnection() throws SQLException {
        return this.dataSource.getConnection();
    }
}
