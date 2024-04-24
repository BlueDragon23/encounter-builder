package dev.aidang.encounters.model.creatures;

import static dev.aidang.encounters.Utils.JSON;
import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import dev.aidang.encounters.TestUtils;
import java.util.List;
import org.junit.jupiter.api.Test;

class FifthEditionDatabaseCreatureTest {

    @Test
    void testDeserialisation() throws JsonProcessingException {
        // when
        String allCreatures = TestUtils.loadResourceAsString("/5e-SRD-Monsters.json");

        // then
        List<FifthEditionDatabaseCreature> fifthEditionDatabaseCreatures =
                JSON.readValue(allCreatures, new TypeReference<>() {});

        // verify
        assertThat(fifthEditionDatabaseCreatures).isNotNull();
        assertThat(fifthEditionDatabaseCreatures).first().satisfies(aboleth -> {
            assertThat(aboleth.name()).isEqualTo("Aboleth");
            assertThat(aboleth.strength()).isEqualTo(21);
            assertThat(aboleth.armorClass()).singleElement().satisfies(ac -> assertThat(ac.value())
                    .isEqualTo(17));
            assertThat(aboleth.actions())
                    .anySatisfy(action -> assertThat(action.name()).isEqualTo("Tentacle"));
        });
    }

    @Test
    void testConversion() throws JsonProcessingException {
        // when
        String allCreatures = TestUtils.loadResourceAsString("/5e-SRD-Monsters.json");
        List<FifthEditionDatabaseCreature> fifthEditionDatabaseCreatures =
                JSON.readValue(allCreatures, new TypeReference<>() {});
        FifthEditionDatabaseCreature aboleth = fifthEditionDatabaseCreatures.get(0);

        // then
        TemplateCreature templateCreature = aboleth.toTemplateCreature();

        // verify
        assertThat(templateCreature).isNotNull();
        assertThat(templateCreature.name()).isEqualTo("Aboleth");
        assertThat(templateCreature.creatureSize()).isEqualTo(CreatureSize.LARGE);
    }
}
