package dev.aidang.encounters.model;

/**
 *
 * Need to have a "template" creature for building, then a "live" creature for running encounter
 * @param name
 * @param hp
 */
public record Creature(String name, int hp) {
}
