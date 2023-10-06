package dev.aidang.encounters.repository;

import static dev.aidang.encounters.TestUtils.getTemplateCreature;
import static org.assertj.core.api.Assertions.assertThat;

import dev.aidang.encounters.IntegrationTest;
import dev.aidang.encounters.TestEncountersApplication;
import dev.aidang.encounters.model.Encounter;
import dev.aidang.encounters.model.EncounterCreatureRef;
import dev.aidang.encounters.model.creatures.TemplateCreature;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

@IntegrationTest
public class EncounterRepositoryIT {

    @Autowired
    private TestEncountersApplication.TestHelper testHelper;

    @Autowired
    private TemplateCreatureRepository templateCreatureRepository;

    @Autowired
    private EncounterRepository sut;

    @BeforeEach
    void setup() {
        testHelper.teardown();
    }

    @Test
    void createEncounter() {
        // when
        Encounter encounter =
                new Encounter(null, "Fight Scene", "Fight scene fight scene we're gonna have a fight scene", List.of());

        // then
        Encounter result = sut.save(encounter);

        // verify
        assertThat(result).isEqualTo(encounter.toBuilder().withId(result.id()).build());
    }

    @Test
    void createEncounterWithCreatures() {
        // when
        TemplateCreature templateCreature = getTemplateCreature().build();
        TemplateCreature templateCreatureSaved = templateCreatureRepository.save(templateCreature);
        EncounterCreatureRef ref = new EncounterCreatureRef(null, AggregateReference.to(templateCreatureSaved.id()), 1);
        Encounter encounter = new Encounter(
                null, "Fight Scene", "Fight scene fight scene we're gonna have a fight scene", List.of(ref));

        // then
        Encounter result = sut.save(encounter);

        // verify
        assertThat(result)
                .isEqualTo(encounter.toBuilder()
                        .withId(result.id())
                        .withCreatures(List.of(ref.toBuilder()
                                .withId(result.creatures().get(0).id())
                                .build()))
                        .build());
    }
}
