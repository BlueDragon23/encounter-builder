package dev.aidang.encounters.model.creatures;

import static dev.aidang.encounters.model.creatures.Speed.parseSpeed;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import dev.aidang.encounters.model.Dice;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Creature model based on the D&D 5e API project
 * <a href="https://www.dnd5eapi.co/docs/#overview">Overview</a>
 */
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record FifthEditionDatabaseCreature(
        String name,
        String desc,
        // If this was implemented, I could use the AbilityScores class
        // https://github.com/FasterXML/jackson-databind/issues/1467
        int strength,
        int dexterity,
        int constitution,
        int intelligence,
        int wisdom,
        int charisma,
        CreatureSize size,
        String type,
        String subtype,
        String alignment,
        List<ArmorClass> armorClass,
        int hitPoints,
        String hitDice,
        String hitPointsRoll,
        List<Action> actions,
        List<Action> legendaryActions,
        float challengeRating,
        int proficiencyBonus,
        List<RefModel> conditionImmunities,
        List<String> damageImmunities,
        List<String> damageResistances,
        List<String> damageVulnerabilities,
        String languages,
        List<Proficiency> proficiencies,
        List<Action> reactions,
        Senses senses,
        List<SpecialAbility> specialAbilities,
        InternalSpeed speed) {

    public TemplateCreature toTemplateCreature() {
        return TemplateCreature.builder(name)
                .withDescription(desc)
                .withAbilityScores(new AbilityScores(strength, dexterity, constitution, intelligence, wisdom, charisma))
                .withCreatureSize(size)
                .withType(type)
                .withAlignment(alignment)
                // This is mostly right
                // Some monsters have a shield, so two AC values
                .withArmorClass(armorClass.get(0).value)
                .withHitpoints(hitPoints)
                .withHitDice(Dice.parse(hitDice))
                .withAttacks(Stream.concat(
                                safeTransform(actions, Action::toAttack).stream(),
                                safeTransform(legendaryActions, Action::toLegendaryAttack).stream())
                        .toList())
                .withSpecialAbilities(safeTransform(specialAbilities, SpecialAbility::toSpecialAbility))
                .withChallengeRating(challengeRating)
                .withProficiencyBonus(proficiencyBonus)
                .withSavingThrows(
                        proficiencies != null
                                ? proficiencies.stream()
                                        .flatMap(proficiency -> proficiency.toSavingThrow().stream())
                                        .toList()
                                : List.of())
                .withSpeed(speed.toSpeed())
                .build();
    }

    private static <T, U> List<U> safeTransform(List<T> items, Function<T, U> transform) {
        if (items == null || items.isEmpty()) {
            return List.of();
        } else {
            return items.stream().map(transform).toList();
        }
    }

    public record RefModel(String index, String name) {}

    // TODO: so many fields
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public record Action(String name, String desc, int attackBonus, List<ActionDamage> damage) {
        public Attack toAttack() {
            return Attack.builder()
                    .withName(name)
                    .withDescription(desc)
                    .withAttackType(AttackType.ACTION)
                    .withDamage(safeTransform(damage, ActionDamage::toDamage))
                    .build();
        }

        public Attack toLegendaryAttack() {
            return Attack.builder()
                    .withName(name)
                    .withDescription(desc)
                    .withAttackType(AttackType.LEGENDARY)
                    .withDamage(safeTransform(damage, ActionDamage::toDamage))
                    .build();
        }
    }

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public record ActionDamage(DamageType damageType, String damageDice) {
        public Damage toDamage() {
            // TODO: the djinni has a choose 1 attack damage, it's insane
            Damage.Builder builder = Damage.builder();
            if (damageType != null) {
                builder.withDamageType(damageType.toDamageType());
            }
            if (damageDice != null) {
                Dice dice = Dice.parse(damageDice);
                builder.withDie(dice.die()).withCount(dice.count());
            }
            return builder.build();
        }
    }

    public record DamageType(String index, String name) {
        public dev.aidang.encounters.model.creatures.DamageType toDamageType() {
            return dev.aidang.encounters.model.creatures.DamageType.valueOf(index.toUpperCase(Locale.ENGLISH));
        }
    }

    // TODO: so many fields
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public record SpecialAbility(String name, String desc, int attackBonus) {
        public dev.aidang.encounters.model.creatures.SpecialAbility toSpecialAbility() {
            return new dev.aidang.encounters.model.creatures.SpecialAbility(name, desc, attackBonus);
        }
    }

    public record ArmorClass(String type, int value, RefModel condition) {}

    public record Proficiency(int value, RefModel proficiency) {
        public Optional<Abilities> toSavingThrow() {
            if (proficiency.index.startsWith("saving-throw")) {
                String[] parts = proficiency.index.split("-");
                return Optional.of(Abilities.fromShortName(parts[parts.length - 1]));
            }
            return Optional.empty();
        }

        public Optional<String> toSkill() {
            if (proficiency.index.startsWith("skill")) {
                String[] parts = proficiency.index.split("-");
                return Optional.of(parts[parts.length - 1]);
            }
            return Optional.empty();
        }
    }

    public record InternalSpeed(String walk, String burrow, String climb, String fly, String swim) {

        public Speed toSpeed() {
            return new Speed(
                    parseSpeed(walk), parseSpeed(burrow), parseSpeed(climb), parseSpeed(fly), parseSpeed(swim));
        }
    }

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public record Senses(
            int passivePerception, String blindsight, String darkvision, String tremorsense, String truesight) {}
}
