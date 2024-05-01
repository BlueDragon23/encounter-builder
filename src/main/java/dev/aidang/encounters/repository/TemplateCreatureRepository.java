package dev.aidang.encounters.repository;

import dev.aidang.encounters.model.creatures.TemplateCreature;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TemplateCreatureRepository
        extends PagingAndSortingRepository<TemplateCreature, Long>, CrudRepository<TemplateCreature, Long> {

    Page<TemplateCreature> findByName(Pageable pageable, String name);
}
