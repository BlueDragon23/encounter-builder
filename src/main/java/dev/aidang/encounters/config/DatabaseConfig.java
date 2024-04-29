package dev.aidang.encounters.config;

import javax.sql.DataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("production")
public class DatabaseConfig {

    /**
     * Configure datasource by injecting properties into the data source builder
     */
    @Bean
    @ConfigurationProperties("app.datasource")
    public DataSource postgres() {
        return DataSourceBuilder.create().build();
    }
}
