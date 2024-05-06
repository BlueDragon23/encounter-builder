package dev.aidang.encounters.repository;

import dev.aidang.encounters.model.Encounter;
import dev.aidang.encounters.model.EncounterSummary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EncounterRepository
        extends PagingAndSortingRepository<Encounter, Long>, CrudRepository<Encounter, Long> {
    Page<EncounterSummary> findAllProjectedBy(Pageable pageable);

    Encounter findByNameIgnoreCase(String name);
}
