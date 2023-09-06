package dev.aidang.encounters.model;

import org.springframework.data.annotation.Id;

public record Encounter(@Id Long id, String name, String description) {
}
