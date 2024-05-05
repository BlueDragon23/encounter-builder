package dev.aidang.encounters.model.creatures;

/**
 * The {@link TemplateCreature} without any embedded/mapped data. This can be used for the
 * list of creatures, without having to do complicated joins or subqueries
 */
public record TemplateCreatureSummary(
        Long id,
        String name,
        String description,
        CreatureSize creatureSize,
        String type,
        String alignment,
        int armorClass,
        float challengeRating,
        int proficiencyBonus,
        int hitpoints) {}
