package dev.aidang.encounters.repository;

import static dev.aidang.encounters.TestUtils.getTemplateCreature;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

import dev.aidang.encounters.IntegrationTest;
import dev.aidang.encounters.TestEncountersApplication;
import dev.aidang.encounters.model.creatures.TemplateCreature;
import java.util.Optional;
import java.util.stream.IntStream;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@IntegrationTest
public class TemplateCreatureRepositoryIT {

    @Autowired
    private TestEncountersApplication.TestHelper testHelper;

    @Autowired
    private TemplateCreatureRepository sut;

    @BeforeEach
    void setup() {
        testHelper.teardown();
    }

    @Test
    void testSave() {
        // when
        TemplateCreature expected = getTemplateCreature().build();

        // then
        TemplateCreature saved = sut.save(expected);

        // verify
        assertThat(saved).isEqualTo(withSavedIds(expected, saved));
    }

    @Test
    void testFindById() {
        // when
        TemplateCreature expected = getTemplateCreature().build();
        TemplateCreature saved = sut.save(expected);

        // then
        Optional<TemplateCreature> result = sut.findById(saved.id());

        // verify
        assertThat(result).hasValue(saved);
    }

    @Test
    void testSearchByName() {
        // when
        TemplateCreature expected = getTemplateCreature().build();
        TemplateCreature other = getTemplateCreature("Other Creature").build();
        TemplateCreature saved = sut.save(expected);
        sut.save(other);

        // then
        Page<TemplateCreature> result = sut.findByName(Pageable.unpaged(), expected.name());

        // verify
        assertThat(result.get()).singleElement().isEqualTo(saved);
    }

    @NotNull
    private static TemplateCreature withSavedIds(TemplateCreature expected, TemplateCreature saved) {
        return expected.toBuilder()
                .withId(saved.id())
                .withAttacks(IntStream.range(0, expected.attacks().size())
                        .mapToObj(i -> expected.attacks().get(i).toBuilder()
                                .withId(saved.attacks().get(i).id())
                                .withDamage(IntStream.range(
                                                0,
                                                expected.attacks()
                                                        .get(i)
                                                        .damage()
                                                        .size())
                                        .mapToObj(j -> expected.attacks().get(i).damage().get(j).toBuilder()
                                                .withId(saved.attacks()
                                                        .get(i)
                                                        .damage()
                                                        .get(j)
                                                        .id())
                                                .build())
                                        .collect(toList()))
                                .build())
                        .collect(toList()))
                .build();
    }
}
