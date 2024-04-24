package dev.aidang.encounters.model;

/**
 * How many dice should be rolled for an event.
 * This may be damage on an attack, total number of hit dice, etc.
 * @param die Which die?
 * @param count How many?
 */
public record Dice(Die die, int count) {

    /**
     * Convert NdX to Dice
     */
    public static Dice parse(String dice) {
        String[] parts = dice.split("d", 2);
        int count = Integer.parseInt(parts[0]);
        Die die = Die.fromInteger(Integer.parseInt(parts[1]));
        return new Dice(die, count);
    }
}
