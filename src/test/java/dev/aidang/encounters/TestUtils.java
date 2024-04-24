package dev.aidang.encounters;

import dev.aidang.encounters.model.Dice;
import dev.aidang.encounters.model.Die;
import dev.aidang.encounters.model.creatures.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

public class TestUtils {

    public static TemplateCreature.Builder getTemplateCreature() {
        return TemplateCreature.builder("Adult Black Dragon")
                .withCreatureSize(CreatureSize.HUGE)
                .withArmorClass(19)
                .withHitpoints(195)
                .withHitDice(new Dice(Die.D12, 17))
                .withAlignment("Chaotic Evil")
                .withSpeed(new Speed(40, 0, 0, 80, 40))
                .withAbilityScores(new AbilityScores(23, 14, 21, 14, 13, 17))
                .withSavingThrows(
                        List.of(Abilities.DEXTERITY, Abilities.CONSTITUTION, Abilities.WISDOM, Abilities.CHARISMA))
                .withAttacks(List.of(Attack.builder()
                        .withName("Bite")
                        .withRange("Melee (10 ft)")
                        .withDamage(List.of(
                                new Damage(Die.D10, 2, DamageType.PIERCING), new Damage(Die.D8, 1, DamageType.ACID)))
                        .build()));
    }

    public static String loadResourceAsString(String path) {
        try (InputStream inputStream = TestUtils.class.getResourceAsStream(path)) {
            return new String(Objects.requireNonNull(inputStream).readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
