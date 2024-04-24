package dev.aidang.encounters.model.creatures;

import java.util.Locale;

public enum Abilities {
    STRENGTH,
    DEXTERITY,
    CONSTITUTION,
    INTELLIGENCE,
    WISDOM,
    CHARISMA;

    public static Abilities fromShortName(String name) {
        return switch (name.toLowerCase(Locale.ENGLISH)) {
            case "str" -> STRENGTH;
            case "dex" -> DEXTERITY;
            case "con" -> CONSTITUTION;
            case "int" -> INTELLIGENCE;
            case "wis" -> WISDOM;
            case "cha" -> CHARISMA;
            default -> throw new IllegalStateException("Unexpected value: " + name.toLowerCase(Locale.ENGLISH));
        };
    }
}
