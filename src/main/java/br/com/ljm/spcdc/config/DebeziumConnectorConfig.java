package br.com.ljm.spcdc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;

@Configuration
public class DebeziumConnectorConfig {

    /**
     * Customer Database Connector Configuration
     */
    @Bean
    public io.debezium.config.Configuration customerConnector() throws IOException {

        File offsetStorageTempFile = File.createTempFile("offsets_", ".dat");
        File dbHistoryTempFile = File.createTempFile("dbhistory_", ".dat");

        return io.debezium.config.Configuration.create()
            .with("name", "customer-mysql-connector")
            .with("connector.class", "io.debezium.connector.mysql.MySqlConnector")
            .with("offset.storage", "org.apache.kafka.connect.storage.FileOffsetBackingStore")
            .with("offset.storage.file.filename", offsetStorageTempFile.getAbsolutePath())
            .with("offset.flush.interval.ms", "60000")
            .with("database.hostname", "db-test-lambda.coyfmkjapkih.us-east-1.rds.amazonaws.com")
            .with("database.port", "3306")
            .with("database.user", "admin")
            .with("database.password", "d4s4_teste$")
            .with("database.dbname", "lab")
            .with("database.include.list", "lab")
            .with("include.schema.changes", "false")
            .with("database.allowPublicKeyRetrieval", "true")
            .with("database.server.id", "10181")
            .with("database.server.name", "customer-mysql-db-server")
            .with("database.history", "io.debezium.relational.history.FileDatabaseHistory")
            .with("database.history.file.filename", dbHistoryTempFile.getAbsolutePath())
            .build();
    }
}