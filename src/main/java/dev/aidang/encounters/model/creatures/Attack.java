package dev.aidang.encounters.model.creatures;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.util.List;

public record Attack(
        @Id Long id, String name, String description, @MappedCollection List<Damage> damage, String range) {

    public static Builder builder() {
        return new Builder();
    }

    public Builder toBuilder() {
        return new Builder()
                .withName(name)
                .withDescription(description)
                .withDamage(damage)
                .withRange(range);
    }

    public static final class Builder {
        private Long id;
        private String name;
        private String description;
        private List<Damage> damage;
        private String range;

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withDamage(List<Damage> damage) {
            this.damage = damage;
            return this;
        }

        public Builder withRange(String range) {
            this.range = range;
            return this;
        }

        public Attack build() {
            return new Attack(id, name, description, damage, range);
        }
    }
}
