package dev.aidang.encounters.model;

import dev.aidang.encounters.model.creatures.TemplateCreature;
import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

public record EncounterCreatureRef(
        @Id Long id, AggregateReference<TemplateCreature, Long> templateCreature, int count) {

    public static Builder builder() {
        return new Builder();
    }

    public Builder toBuilder() {
        return new Builder().withId(id).withTemplateCreature(templateCreature).withCount(count);
    }

    public static class Builder {

        private Long id;
        private AggregateReference<TemplateCreature, Long> templateCreature;
        private int count;

        private Builder() {
            // Do nothing
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withTemplateCreature(AggregateReference<TemplateCreature, Long> templateCreature) {
            this.templateCreature = templateCreature;
            return this;
        }

        public Builder withTemplateCreature(TemplateCreature creature) {
            this.templateCreature = AggregateReference.to(creature.id());
            return this;
        }

        public Builder withCount(int count) {
            this.count = count;
            return this;
        }

        public EncounterCreatureRef build() {
            return new EncounterCreatureRef(id, templateCreature, count);
        }
    }
}
