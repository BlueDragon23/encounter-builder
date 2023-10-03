package dev.aidang.encounters.service;

import dev.aidang.encounters.model.Dice;
import dev.aidang.encounters.model.Die;
import dev.aidang.encounters.model.creatures.*;
import org.assertj.core.api.AutoCloseableSoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

class TemplateCreatureServiceTest {

    private TemplateCreatureService sut;

    @BeforeEach
    void setup() {
        this.sut = new TemplateCreatureService();
    }

    @Test
    void testImportFromMPMB() throws IOException {
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
            softly.assertThat(templateCreature.speed()).isEqualTo(new Speed(0, 0, 90, 0));
            softly.assertThat(templateCreature.abilityScores()).isEqualTo(new AbilityScores(14, 20, 14, 6, 10, 6));
            Attack first = Attack.builder()
                    .withName("Slam")
                    .withDamage(new Dice(Die.D8, 2))
                    .withDamageType(DamageType.BLUDGEONING)
                    .withDescription("Two slam attacks as an Attack action")
                    .withRange("Melee (5 ft)")
                    .build();
            Attack second = Attack.builder()
                    .withName("Whirlwind (Recharge 4-6)")
                    .withDamage(new Dice(Die.D8, 3))
                    .withDamageType(DamageType.BLUDGEONING)
                    .withDescription(
                            "Str save; fail\u2015 flung 20 ft random direction, prone; success\u2015 half damage")
                    .withRange("All in shared area")
                    .build();
            softly.assertThat(templateCreature.attacks()).containsExactly(first, second);
        }
    }
}