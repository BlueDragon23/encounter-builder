package dev.aidang.encounters.model;

import static dev.aidang.encounters.Utils.JSON;
import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

@ExtendWith(MockitoExtension.class)
class EncounterTest {

    private static final String SERIALISED_ENCOUNTER =
            """
            {
                "name": "Test Encounter",
                "description": "This is a random description of my encounter",
                "creatures": [{
                    "templateCreature": {
                        "id": 10
                    },
                    "count": 5
                }]
            }
            """;
    private static final Encounter ENCOUNTER = Encounter.builder()
            .withName("Test Encounter")
            .withDescription("This is a random description of my encounter")
            .withCreatures(List.of(EncounterCreatureRef.builder()
                    .withTemplateCreature(AggregateReference.to(10L))
                    .withCount(5)
                    .build()))
            .build();
    ;

    @Test
    void testSerialisation() throws JsonProcessingException {
        // when/then
        String serialised = JSON.writeValueAsString(ENCOUNTER);

        // verify
        assertThat(serialised).isEqualToIgnoringWhitespace(SERIALISED_ENCOUNTER);
    }

    @Test
    void testDeserialisation() throws JsonProcessingException {
        // when/then
        Encounter encounter = JSON.readValue(SERIALISED_ENCOUNTER, Encounter.class);

        // verify
        assertThat(encounter).isEqualTo(ENCOUNTER);
    }
}
