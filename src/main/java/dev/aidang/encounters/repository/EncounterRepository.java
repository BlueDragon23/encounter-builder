package dev.aidang.encounters.repository;

import dev.aidang.encounters.model.Encounter;
import org.springframework.data.repository.CrudRepository;

public interface EncounterRepository extends CrudRepository<Encounter, Long> {
}
