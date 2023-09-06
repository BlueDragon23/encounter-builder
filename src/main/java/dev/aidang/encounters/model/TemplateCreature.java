package dev.aidang.encounters.model;

import org.springframework.data.annotation.Id;

/**
 * Need to have a "template" creature for building, then a "live" creature for running encounter
 */
public record TemplateCreature(@Id Long id, String name, String description, int hp) {
}
