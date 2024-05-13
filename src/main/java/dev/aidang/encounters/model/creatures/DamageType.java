package dev.aidang.encounters.model.creatures;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

// Match convention of existing data
@JsonNaming(PropertyNamingStrategies.LowerCaseStrategy.class)
public enum DamageType {
    SLASHING,
    PIERCING,
    BLUDGEONING,
    POISON,
    ACID,
    FIRE,
    COLD,
    RADIANT,
    NECROTIC,
    LIGHTNING,
    THUNDER,
    FORCE,
    PSYCHIC;
}
