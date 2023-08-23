package dev.aidang.encounters.repository;

import dev.aidang.encounters.model.Encounter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EncounterRepository extends JpaRepository<Encounter, Long> {
}
