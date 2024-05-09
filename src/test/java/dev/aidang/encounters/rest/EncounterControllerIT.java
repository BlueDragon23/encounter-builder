package dev.aidang.encounters.rest;

import static dev.aidang.encounters.Utils.JSON;
import static org.assertj.core.api.Assertions.assertThat;

import dev.aidang.encounters.IntegrationTest;
import dev.aidang.encounters.model.Encounter;
import dev.aidang.encounters.model.EncounterCreatureRef;
import dev.aidang.encounters.model.creatures.TemplateCreature;
import dev.aidang.encounters.repository.EncounterRepository;
import dev.aidang.encounters.repository.TemplateCreatureRepository;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.MimeTypeUtils;

@IntegrationTest
class EncounterControllerIT extends RestTestBase {

    @Autowired
    private EncounterRepository encounterRepository;

    @Autowired
    private TemplateCreatureRepository templateCreatureRepository;

    @Test
    void testCreateEncounter() throws IOException, InterruptedException {
        // when
        Encounter requestEncounter = new Encounter(null, "name", "description", List.of());
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + port + "/encounters"))
                .header("Content-Type", MimeTypeUtils.APPLICATION_JSON_VALUE)
                .POST(HttpRequest.BodyPublishers.ofString(JSON.writeValueAsString(requestEncounter)))
                .build();

        // then rest request
        HttpResponse<String> response =
                httpClient.send(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));

        // verify
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        Encounter responseEncounter = JSON.readValue(response.body(), Encounter.class);
        assertThat(responseEncounter.description()).isEqualTo(requestEncounter.description());
        assertThat(responseEncounter.name()).isEqualTo(requestEncounter.name());
    }

    @Test
    void testUpdateEncounter() throws IOException, InterruptedException {
        // when
        TemplateCreature creature = templateCreatureRepository.save(
                TemplateCreature.builder("creature").build());
        Encounter existing = encounterRepository.save(new Encounter(null, "name", "description", List.of()));
        Encounter requestEncounter = existing.toBuilder()
                .withCreatures(List.of(EncounterCreatureRef.builder()
                        .withTemplateCreature(creature)
                        .withCount(4)
                        .build()))
                .build();
        String serialisedEncounter = JSON.writeValueAsString(requestEncounter);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + port + "/encounters/" + existing.id() + "/edit"))
                .header("Content-Type", MimeTypeUtils.APPLICATION_JSON_VALUE)
                .POST(HttpRequest.BodyPublishers.ofString(serialisedEncounter))
                .build();

        // then rest request
        HttpResponse<String> response =
                httpClient.send(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));

        // verify
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        Encounter responseEncounter = JSON.readValue(response.body(), Encounter.class);
        assertThat(responseEncounter.description()).isEqualTo(requestEncounter.description());
        assertThat(responseEncounter.name()).isEqualTo(requestEncounter.name());
    }
}
