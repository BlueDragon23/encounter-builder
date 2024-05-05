package dev.aidang.encounters.model.creatures;

import dev.aidang.encounters.model.Dice;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.MappedCollection;

/**
 * Need to have a "template" creature for building, then a "live" creature for running encounter
 */
public record TemplateCreature(
        @Id Long id,
        String name,
        String description,
        CreatureSize creatureSize,
        String type,
        String alignment,
        int armorClass,
        float challengeRating,
        int proficiencyBonus,
        int hitpoints,
        @Embedded.Empty(prefix = "dice_") Dice hitDice,
        @Embedded.Empty(prefix = "speed_") Speed speed,
        @Embedded.Empty(prefix = "ability_") AbilityScores abilityScores,
        // TODO: child tables?
        @MappedCollection List<Attack> attacks,
        @MappedCollection List<SpecialAbility> specialAbilities,
        List<Abilities> savingThrows) {

    public static Builder builder(String name) {
        return new Builder(name);
    }

    public Builder toBuilder() {
        return new Builder(name)
                .withDescription(description)
                .withCreatureSize(creatureSize)
                .withType(type)
                .withAlignment(alignment)
                .withArmorClass(armorClass)
                .withHitpoints(hitpoints)
                .withHitDice(hitDice)
                .withSpeed(speed)
                .withAttacks(attacks)
                .withSpecialAbilities(specialAbilities)
                .withAbilityScores(abilityScores)
                .withSavingThrows(savingThrows);
    }

    public TemplateCreatureSummary toSummary() {
        return new TemplateCreatureSummary(
                id,
                name,
                description,
                creatureSize,
                type,
                alignment,
                armorClass,
                challengeRating,
                proficiencyBonus,
                hitpoints);
    }

    public static final class Builder {

        private Long id;
        private final String name;
        private String description;
        private CreatureSize creatureSize;
        private String type;
        private String alignment;
        private int armorClass;
        private float challengeRating;
        private int proficiencyBonus;
        private int hitpoints;
        private Dice hitDice;
        private Speed speed;
        private AbilityScores abilityScores;
        private List<Attack> attacks = List.of();
        private List<SpecialAbility> specialAbilities = List.of();
        private List<Abilities> savingThrows = List.of();

        private Builder(String name) {
            this.name = name;
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withCreatureSize(CreatureSize creatureSize) {
            this.creatureSize = creatureSize;
            return this;
        }

        public Builder withType(String type) {
            this.type = type;
            return this;
        }

        public Builder withAlignment(String alignment) {
            this.alignment = alignment;
            return this;
        }

        public Builder withArmorClass(int armorClass) {
            this.armorClass = armorClass;
            return this;
        }

        public Builder withChallengeRating(float challengeRating) {
            this.challengeRating = challengeRating;
            return this;
        }

        public Builder withProficiencyBonus(int proficiencyBonus) {
            this.proficiencyBonus = proficiencyBonus;
            return this;
        }

        public Builder withHitpoints(int hitpoints) {
            this.hitpoints = hitpoints;
            return this;
        }

        public Builder withHitDice(Dice hitDice) {
            this.hitDice = hitDice;
            return this;
        }

        public Builder withSpeed(Speed speed) {
            this.speed = speed;
            return this;
        }

        public Builder withAbilityScores(AbilityScores abilityScores) {
            this.abilityScores = abilityScores;
            return this;
        }

        public Builder withAttacks(List<Attack> attacks) {
            this.attacks = attacks;
            return this;
        }

        public Builder withSpecialAbilities(List<SpecialAbility> specialAbilities) {
            this.specialAbilities = specialAbilities;
            return this;
        }

        public Builder withSavingThrows(List<Abilities> abilities) {
            this.savingThrows = abilities;
            return this;
        }

        public TemplateCreature build() {
            return new TemplateCreature(
                    id,
                    name,
                    description,
                    creatureSize,
                    type,
                    alignment,
                    armorClass,
                    challengeRating,
                    proficiencyBonus,
                    hitpoints,
                    hitDice,
                    speed,
                    abilityScores,
                    attacks,
                    specialAbilities,
                    savingThrows);
        }
    }
}
