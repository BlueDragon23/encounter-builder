package dev.aidang.encounters.repository;

import dev.aidang.encounters.model.creatures.TemplateCreature;
import dev.aidang.encounters.model.creatures.TemplateCreatureSummary;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TemplateCreatureRepository
        extends PagingAndSortingRepository<TemplateCreature, Long>, CrudRepository<TemplateCreature, Long> {

    Page<TemplateCreatureSummary> findAllProjectedBy(Pageable pageable);

    @Query("SELECT * FROM template_creature t WHERE t.name ILIKE CONCAT('%', :name, '%') LIMIT :limit OFFSET :offset")
    List<TemplateCreatureSummary> findSummaryByNameContaining(String name, int limit, long offset);

    @Query("SELECT COUNT(*) FROM template_creature t WHERE t.name ILIKE CONCAT('%', :name, '%')")
    int countSummaryByNameContaining(String name);

    Page<TemplateCreature> findByNameContaining(String name, Pageable pageable);
}
