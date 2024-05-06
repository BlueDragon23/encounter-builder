package dev.aidang.encounters.rest;

import static dev.aidang.encounters.Utils.JSON;
import static org.assertj.core.api.Assertions.assertThat;

import dev.aidang.encounters.IntegrationTest;
import dev.aidang.encounters.model.Encounter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.util.MimeTypeUtils;

@IntegrationTest
class EncounterControllerIT extends RestTestBase {

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
}
