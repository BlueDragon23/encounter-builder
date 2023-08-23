package dev.aidang.encounters.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.List;

@Entity
public record Encounter(@Id @GeneratedValue Long id, String name, String description, List<Creature> creatures) {
}
