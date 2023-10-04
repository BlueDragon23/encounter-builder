package dev.aidang.encounters.repository;

import dev.aidang.encounters.IntegrationTest;
import dev.aidang.encounters.model.Dice;
import dev.aidang.encounters.model.Die;
import dev.aidang.encounters.model.creatures.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@IntegrationTest
public class TemplateCreatureRepositoryIT {

    @Autowired
    private TemplateCreatureRepository templateCreatureRepository;

    @Test
    void testSave() {
        // when
        TemplateCreature expected = TemplateCreature.builder("Adult Black Dragon")
                .withCreatureSize(CreatureSize.HUGE)
                .withArmorClass(19)
                .withHitpoints(195)
                .withHitDice(new Dice(Die.D12, 17))
                .withAlignment("Chaotic Evil")
                .withSpeed(new Speed(40, 0, 80, 40))
                .withAbilityScores(new AbilityScores(23, 14, 21, 14, 13, 17))
                .withSavingThrows(
                        List.of(Abilities.DEXTERITY, Abilities.CONSTITUTION, Abilities.WISDOM, Abilities.CHARISMA))
                .withAttacks(List.of(Attack.builder()
                        .withName("Bite")
                        .withRange("Melee (10 ft)")
                        .withDamage(List.of(
                                new Damage(Die.D10, 2, DamageType.PIERCING), new Damage(Die.D8, 1, DamageType.ACID)))
                        .build()))
                .build();

        // then
        TemplateCreature saved = templateCreatureRepository.save(expected);

        // verify
        // TODO: ignore generated IDs
        assertThat(saved).isEqualTo(expected.toBuilder().withId(saved.id()).build());
    }
}
