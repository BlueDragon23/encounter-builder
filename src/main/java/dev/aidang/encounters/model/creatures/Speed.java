package dev.aidang.encounters.model.creatures;

public record Speed(int walk, int climb, int fly, int swim) {

    public Speed(int walk) {
        this(walk, 0, 0, 0);
    }
}
