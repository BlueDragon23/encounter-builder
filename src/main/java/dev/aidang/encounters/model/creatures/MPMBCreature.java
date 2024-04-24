package dev.aidang.encounters.model.creatures;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import dev.aidang.encounters.model.Dice;
import dev.aidang.encounters.model.Die;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record MPMBCreature(
        String name,
        List<String> nameAlt,
        List<List<String>> source,
        int size,
        String type,
        String alignment,
        int ac,
        int hp,
        List<Integer> hd,
        String speed,
        List<Integer> scores,
        String damageResistances,
        String damageImmunities,
        String conditionImmunities,
        String senses,
        int passivePerception,
        String languages,
        String challengeRating,
        int proficiencyBonus,
        int attacksAction,
        List<Attack> attacks,
        List<Trait> traits,
        List<Action> actions) {

    public TemplateCreature toTemplateCreature() {
        return TemplateCreature.builder(name)
                .withDescription(name)
                .withCreatureSize(toCreatureSize(size))
                .withType(type)
                .withAlignment(alignment)
                .withArmorClass(ac)
                .withHitDice(new Dice(Die.fromInteger(hd().get(1)), hd().get(0)))
                // TODO: maybe fix this
                .withSpeed(new Speed(30))
                .withAbilityScores(new AbilityScores(
                        scores.get(0), scores.get(1), scores.get(2), scores.get(3), scores.get(4), scores.get(5)))
                .withHitpoints(hp)
                .withAttacks(attacks.stream().map(Attack::toAttack).collect(Collectors.toList()))
                .build();
    }

    private CreatureSize toCreatureSize(int mpmbSize) {
        return switch (mpmbSize) {
            case 5 -> CreatureSize.TINY;
            case 4 -> CreatureSize.SMALL;
            case 3 -> CreatureSize.MEDIUM;
            case 2 -> CreatureSize.LARGE;
            case 1 -> CreatureSize.HUGE;
            case 0 -> CreatureSize.GARGANTUAN;
            default -> throw new IllegalArgumentException("Unexpected size " + mpmbSize);
        };
    }

    public record Attack(String name, int ability, List<String> damage, String range, String description, Boolean dc) {

        private dev.aidang.encounters.model.creatures.Attack toAttack() {
            // Order is dice count, dice type, damage type
            DamageType damageType = DamageType.valueOf(damage.get(2).toUpperCase(Locale.ENGLISH));
            return dev.aidang.encounters.model.creatures.Attack.builder()
                    .withName(name)
                    .withDescription(description)
                    .withDamage(List.of(new Damage(
                            Die.fromInteger(Integer.parseInt(damage.get(1))),
                            Integer.parseInt(damage.get(0)),
                            damageType)))
                    .withRange(range)
                    .build();
        }
    }

    public record Trait(String name, String description) {}

    public record Action(String name, String description) {}
}
