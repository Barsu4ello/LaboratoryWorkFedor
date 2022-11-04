package com.cvetkov.fedor.laboratoryWork.initialiser;

import lombok.experimental.UtilityClass;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;

@UtilityClass
public class Postgres {

    public final PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:12.3");

    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + container.getJdbcUrl(),
                    "spring.datasource.username=" + container.getUsername(),
                    "spring.datasource.password=" + container.getPassword(),
                    "spring.liquibase.enabled=" + true,
                    "spring.liquibase.url=" + container.getJdbcUrl(),
                    "spring.liquibase.user=" + container.getUsername(),
                    "spring.liquibase.password=" + container.getPassword()
            ).applyTo(applicationContext);
        }
    }
}
