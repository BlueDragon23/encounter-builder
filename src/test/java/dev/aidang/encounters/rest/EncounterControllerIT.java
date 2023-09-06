package dev.aidang.encounters.rest;

import dev.aidang.encounters.IntegrationTest;
import dev.aidang.encounters.model.Encounter;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

import static dev.aidang.encounters.Utils.JSON;
import static org.assertj.core.api.Assertions.assertThat;

@IntegrationTest
class EncounterControllerIT extends RestTestBase {

    @Test
    void testCreateEncounter() throws IOException, InterruptedException {
        // when
        Encounter requestEncounter = new Encounter(null, "name", "description");
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create("http://localhost:" + port + "/encounter"))
                .POST(HttpRequest.BodyPublishers.ofString(JSON.writeValueAsString(requestEncounter)))
                .build();

        // then rest request
        HttpResponse<String> response = httpClient.send(request,
                HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));

        // verify
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        Encounter responseEncounter = JSON.readValue(response.body(), Encounter.class);
        assertThat(responseEncounter).isEqualTo(requestEncounter);
    }
}
