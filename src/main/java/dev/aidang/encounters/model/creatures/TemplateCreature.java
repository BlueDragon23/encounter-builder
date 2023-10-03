package dev.aidang.encounters.model.creatures;

import dev.aidang.encounters.model.Dice;
import org.springframework.data.annotation.Id;

import java.util.List;

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
        int hitpoints,
        Dice hitDice,
        Speed speed,
        AbilityScores abilityScores,
        List<Attack> attacks) {

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
                .withAbilityScores(abilityScores);
    }

    public static final class Builder {

        private Long id;
        private final String name;
        private String description;
        private CreatureSize creatureSize;
        private String type;
        private String alignment;
        private int armorClass;
        private int hitpoints;
        private Dice hitDice;
        private Speed speed;
        private AbilityScores abilityScores;
        private List<Attack> attacks;

        private Builder(String name) {
            this.name = name;
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

        public TemplateCreature build() {
            return new TemplateCreature(
                    id,
                    name,
                    description,
                    creatureSize,
                    type,
                    alignment,
                    armorClass,
                    hitpoints,
                    hitDice,
                    speed,
                    abilityScores,
                    attacks);
        }
    }
}
