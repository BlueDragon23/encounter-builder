package dev.aidang.encounters;

import dev.aidang.encounters.repository.EncounterCreatureRefRepository;
import dev.aidang.encounters.repository.EncounterRepository;
import dev.aidang.encounters.repository.TemplateCreatureRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.devtools.restart.RestartScope;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;

@TestConfiguration(proxyBeanMethods = false)
public class TestEncountersApplication {

    @Bean
    @ServiceConnection
    @RestartScope
    PostgreSQLContainer<?> postgreSQLContainer() {
        return new PostgreSQLContainer<>("postgres:latest");
    }

    @Bean
    TestHelper testHelper(
            EncounterRepository encounterRepository,
            TemplateCreatureRepository templateCreatureRepository,
            EncounterCreatureRefRepository encounterCreatureRefRepository) {
        return new TestHelper(encounterRepository, templateCreatureRepository, encounterCreatureRefRepository);
    }

    public static void main(String[] args) {
        SpringApplication.from(EncountersApplication::main)
                .with(TestEncountersApplication.class)
                .run(args);
    }

    public static class TestHelper {

        private final EncounterRepository encounterRepository;
        private final TemplateCreatureRepository templateCreatureRepository;
        private final EncounterCreatureRefRepository encounterCreatureRefRepository;

        public TestHelper(
                EncounterRepository encounterRepository,
                TemplateCreatureRepository templateCreatureRepository,
                EncounterCreatureRefRepository encounterCreatureRefRepository) {
            this.encounterRepository = encounterRepository;
            this.templateCreatureRepository = templateCreatureRepository;
            this.encounterCreatureRefRepository = encounterCreatureRefRepository;
        }

        public void teardown() {
            encounterCreatureRefRepository.deleteAll();
            templateCreatureRepository.deleteAll();
            encounterRepository.deleteAll();
        }
    }
}
