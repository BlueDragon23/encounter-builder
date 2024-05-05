package dev.aidang.encounters.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import dev.aidang.encounters.model.Dice;
import dev.aidang.encounters.model.Die;
import dev.aidang.encounters.model.creatures.AbilityScores;
import dev.aidang.encounters.model.creatures.Attack;
import dev.aidang.encounters.model.creatures.CreatureSize;
import dev.aidang.encounters.model.creatures.Damage;
import dev.aidang.encounters.model.creatures.DamageType;
import dev.aidang.encounters.model.creatures.TemplateCreature;
import dev.aidang.encounters.model.creatures.TemplateCreatureSummary;
import dev.aidang.encounters.repository.TemplateCreatureRepository;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.assertj.core.api.AutoCloseableSoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
class TemplateCreatureServiceTest {

    @Mock
    private TemplateCreatureRepository templateCreatureRepository;

    private TemplateCreatureService sut;

    @BeforeEach
    void setup() {
        this.sut = new TemplateCreatureService(templateCreatureRepository);
    }

    @Nested
    class SearchMonsters {

        @Mock
        private TemplateCreatureSummary templateCreatureSummary;

        private static final int PAGE_SIZE = 5;
        private final Pageable pageable = Pageable.ofSize(PAGE_SIZE);

        @Test
        void testNoName() {
            // when
            when(templateCreatureRepository.findAllProjectedBy(pageable))
                    .thenReturn(new PageImpl<>(List.of(templateCreatureSummary)));

            // then
            Page<TemplateCreatureSummary> templateCreatureSummaries = sut.searchMonsters(pageable, null);

            // verify
            assertThat(templateCreatureSummaries.getTotalElements()).isEqualTo(1);
            assertThat(templateCreatureSummaries.get()).containsExactly(templateCreatureSummary);
        }

        @Test
        void testFewResults() {
            // when
            String creatureName = "Acolyte";
            when(templateCreatureRepository.findSummaryByNameContaining(creatureName, PAGE_SIZE, 0))
                    .thenReturn(List.of(templateCreatureSummary));

            // then
            Page<TemplateCreatureSummary> templateCreatureSummaries = sut.searchMonsters(pageable, creatureName);

            // verify
            assertThat(templateCreatureSummaries.getTotalElements()).isEqualTo(1);
            assertThat(templateCreatureSummaries.get()).containsExactly(templateCreatureSummary);
            assertThat(templateCreatureSummaries.getTotalPages()).isEqualTo(1);
        }

        @Test
        void testManyResults() {
            // when
            List<TemplateCreatureSummary> firstPage = List.of(
                    templateCreatureSummary,
                    templateCreatureSummary,
                    templateCreatureSummary,
                    templateCreatureSummary,
                    templateCreatureSummary);
            String creatureName = "Dragon";
            when(templateCreatureRepository.findSummaryByNameContaining(creatureName, PAGE_SIZE, 0))
                    .thenReturn(firstPage);
            when(templateCreatureRepository.countSummaryByNameContaining(creatureName))
                    .thenReturn(17);

            // then
            Page<TemplateCreatureSummary> templateCreatureSummaries = sut.searchMonsters(pageable, creatureName);

            // verify
            assertThat(templateCreatureSummaries.getTotalElements()).isEqualTo(17);
            assertThat(templateCreatureSummaries.get()).containsExactlyElementsOf(firstPage);
            assertThat(templateCreatureSummaries.getTotalPages()).isEqualTo(4);
        }
    }

    @Nested
    class TestImport {

        @BeforeEach
        void setup() {
            when(templateCreatureRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));
        }

        @Test
        void testImportFromMPMB__airElemental() throws IOException {
            // when
            String path = "/creatures/air_elemental.json";
            InputStream in = this.getClass().getResourceAsStream(path);

            // then
            TemplateCreature templateCreature = sut.importCreatureFromMPMB(in);

            // verify
            try (var softly = new AutoCloseableSoftAssertions()) {
                softly.assertThat(templateCreature.name()).isEqualTo("Air Elemental");
                softly.assertThat(templateCreature.creatureSize()).isEqualTo(CreatureSize.LARGE);
                softly.assertThat(templateCreature.armorClass()).isEqualTo(15);
                softly.assertThat(templateCreature.hitpoints()).isEqualTo(90);
                //            softly.assertThat(templateCreature.speed()).isEqualTo(new Speed(0, 0, 90, 0));
                softly.assertThat(templateCreature.abilityScores()).isEqualTo(new AbilityScores(14, 20, 14, 6, 10, 6));
                Attack first = Attack.builder()
                        .withName("Slam")
                        .withDamage(List.of(new Damage(Die.D8, 2, DamageType.BLUDGEONING)))
                        .withDescription("Two slam attacks as an Attack action")
                        .withRange("Melee (5 ft)")
                        .build();
                Attack second = Attack.builder()
                        .withName("Whirlwind (Recharge 4-6)")
                        .withDamage(List.of(new Damage(Die.D8, 3, DamageType.BLUDGEONING)))
                        .withDescription(
                                "Str save; fail\u2015 flung 20 ft random direction, prone; success\u2015 half damage")
                        .withRange("All in shared area")
                        .build();
                softly.assertThat(templateCreature.attacks()).containsExactly(first, second);
            }
        }

        @Test
        void testImportFromMPMB__trex() throws IOException {
            // when
            String path = "/creatures/t_rex.json";
            InputStream in = this.getClass().getResourceAsStream(path);

            // then
            TemplateCreature templateCreature = sut.importCreatureFromMPMB(in);

            // verify
            try (var softly = new AutoCloseableSoftAssertions()) {
                softly.assertThat(templateCreature.name()).isEqualTo("Tyrannosaurus Rex");
                softly.assertThat(templateCreature.creatureSize()).isEqualTo(CreatureSize.HUGE);
                softly.assertThat(templateCreature.armorClass()).isEqualTo(13);
                softly.assertThat(templateCreature.hitDice()).isEqualTo(new Dice(Die.D12, 13));
                softly.assertThat(templateCreature.hitpoints()).isEqualTo(136);
                //            softly.assertThat(templateCreature.speed()).isEqualTo(new Speed(50));
                softly.assertThat(templateCreature.abilityScores()).isEqualTo(new AbilityScores(25, 10, 19, 2, 12, 9));
                Attack first = Attack.builder()
                        .withName("Bite")
                        .withDamage(List.of(new Damage(Die.D12, 4, DamageType.PIERCING)))
                        .withDescription(
                                "Medium or smaller target is grappled and restrained (escape DC 17); Can't use bite again until grapple ends")
                        .withRange("Melee (10 ft)")
                        .build();
                Attack second = Attack.builder()
                        .withName("Tail")
                        .withDamage(List.of(new Damage(Die.D8, 3, DamageType.BLUDGEONING)))
                        .withDescription("One bite and one tail attack to different targets as an Attack action")
                        .withRange("Melee (10 ft)")
                        .build();
                softly.assertThat(templateCreature.attacks()).containsExactly(first, second);
            }
        }
    }
}
