package dev.aidang.encounters.model.creatures;

public record Speed(int walk, int burrow, int climb, int fly, int swim) {

    public Speed(int walk) {
        this(walk, 0, 0, 0, 0);
    }

    /**
     * Convert the string "10 ft." to a number
     */
    public static int parseSpeed(String speed) {
        if (speed != null) {
            // Very optimistic parsing
            return Integer.parseInt(speed.split(" ")[0]);
        }
        return 0;
    }
}
