package dev.aidang.encounters.model.creatures;

import dev.aidang.encounters.model.Die;
import org.springframework.data.annotation.Id;

/**
 * The damage dealt by an attack
 * @param die The type of die
 * @param count The number to roll
 * @param damageType The type of damage to deal
 */
public record Damage(@Id Long id, Die die, int count, int modifier, DamageType damageType) {

    public Damage(Die die, int count, int modifier, DamageType damageType) {
        this(null, die, count, modifier, damageType);
    }

    public static Builder builder() {
        return new Builder();
    }

    public Builder toBuilder() {
        return new Builder().withId(id).withDie(die).withCount(count).withDamageType(damageType);
    }

    public static class Builder {

        private Long id;
        private Die die;
        private int count;
        private int modifier;
        private DamageType damageType;

        private Builder() {
            // Do nothing
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withDie(Die die) {
            this.die = die;
            return this;
        }

        public Builder withCount(int count) {
            this.count = count;
            return this;
        }

        public Builder withModifier(int modifier) {
            this.modifier = modifier;
            return this;
        }

        public Builder withDamageType(DamageType damageType) {
            this.damageType = damageType;
            return this;
        }

        public Damage build() {
            return new Damage(id, die, count, modifier, damageType);
        }
    }
}
