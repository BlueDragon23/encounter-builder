package dev.aidang.encounters.model;

/**
 * How many dice should be rolled for an event.
 * This may be damage on an attack, total number of hit dice, etc.
 * @param die Which die?
 * @param count How many?
 */
public record Dice(Die die, int count) {}
