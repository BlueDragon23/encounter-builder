package dev.aidang.encounters.model.creatures;

import dev.aidang.encounters.model.Dice;

public record Attack(String name, String description, Dice damage, DamageType damageType, String range) {

    public static Builder builder() {
        return new Builder();
    }

    public Builder toBuilder() {
        return new Builder()
                .withName(name)
                .withDescription(description)
                .withDamage(damage)
                .withDamageType(damageType)
                .withRange(range);
    }

    public static final class Builder {
        private String name;
        private String description;
        private Dice damage;
        private DamageType damageType;
        private String range;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withDamage(Dice damage) {
            this.damage = damage;
            return this;
        }

        public Builder withDamageType(DamageType damageType) {
            this.damageType = damageType;
            return this;
        }

        public Builder withRange(String range) {
            this.range = range;
            return this;
        }

        public Attack build() {
            return new Attack(name, description, damage, damageType, range);
        }
    }
}
