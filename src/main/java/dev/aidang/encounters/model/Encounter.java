package dev.aidang.encounters.model;

import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;

public record Encounter(
        @Id Long id, String name, String description, @MappedCollection List<EncounterCreatureRef> creatures) {

    public static Builder builder() {
        return new Builder();
    }

    public Builder toBuilder() {
        return new Builder()
                .withId(id)
                .withName(name)
                .withDescription(description)
                .withCreatures(creatures);
    }

    public static class Builder {
        private Long id;
        private String name;
        private String description;
        private List<EncounterCreatureRef> creatures;

        private Builder() {
            // Do nothing
        }

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

        public Builder withCreatures(List<EncounterCreatureRef> creatures) {
            this.creatures = creatures;
            return this;
        }

        public Encounter build() {
            return new Encounter(id, name, description, creatures);
        }
    }
}
