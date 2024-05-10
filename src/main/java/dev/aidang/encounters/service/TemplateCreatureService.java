package dev.aidang.encounters.service;

import static dev.aidang.encounters.Utils.JSON;

import com.fasterxml.jackson.core.type.TypeReference;
import dev.aidang.encounters.NotFoundException;
import dev.aidang.encounters.model.creatures.CreatureSize;
import dev.aidang.encounters.model.creatures.FifthEditionDatabaseCreature;
import dev.aidang.encounters.model.creatures.MPMBCreature;
import dev.aidang.encounters.model.creatures.TemplateCreature;
import dev.aidang.encounters.model.creatures.TemplateCreatureSummary;
import dev.aidang.encounters.repository.TemplateCreatureRepository;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class TemplateCreatureService {

    private static final Logger log = LoggerFactory.getLogger(TemplateCreatureService.class);
    private final TemplateCreatureRepository templateCreatureRepository;

    public TemplateCreatureService(TemplateCreatureRepository templateCreatureRepository) {
        this.templateCreatureRepository = templateCreatureRepository;
        log.info("Instantiated TemplateCreatureService");
    }

    @PostConstruct
    public void bulkImport() {
        try (InputStream inputStream = TemplateCreatureService.class.getResourceAsStream("/5e-SRD-Monsters.json")) {
            List<FifthEditionDatabaseCreature> creatures = JSON.readValue(inputStream, new TypeReference<>() {});
            log.info("Inserting {} creatures into the database", creatures.size());
            templateCreatureRepository.saveAll(creatures.stream()
                    .map(FifthEditionDatabaseCreature::toTemplateCreature)
                    .toList());
        } catch (IOException e) {
            log.error("Failed to import creatures from SRD", e);
            throw new RuntimeException("Failed to import creatures from SRD", e);
        }
    }

    public Page<TemplateCreatureSummary> searchMonsters(Pageable pageable, String name) {
        if (name == null) {
            return templateCreatureRepository.findAllProjectedBy(pageable);
        }
        List<TemplateCreatureSummary> items = templateCreatureRepository.findSummaryByNameContaining(
                name, pageable.getPageSize(), pageable.getOffset());
        if (pageable.getPageNumber() == 0 && items.size() < pageable.getPageSize()) {
            // Optimisation for narrow searches
            return new PageImpl<>(items, pageable, items.size());
        } else {
            int total = templateCreatureRepository.countSummaryByNameContaining(name);
            return new PageImpl<>(items, pageable, total);
        }
    }

    public TemplateCreature getMonster(Long id) {
        Optional<TemplateCreature> creature = templateCreatureRepository.findById(id);
        if (creature.isEmpty()) {
            throw new NotFoundException("No monster exists with ID %d".formatted(id));
        }
        return creature.get();
    }

    public TemplateCreature createMonster(TemplateCreature creature) {
        return templateCreatureRepository.save(creature);
    }

    public TemplateCreature importCreatureFromFifthEditionDatabase(
            FifthEditionDatabaseCreature fifthEditionDatabaseCreature) {
        TemplateCreature templateCreature = fifthEditionDatabaseCreature.toTemplateCreature();
        return templateCreatureRepository.save(templateCreature);
    }

    public TemplateCreature importCreatureFromMPMB(InputStream inputStream) throws IOException {
        MPMBCreature mpmbCreature = JSON.readValue(inputStream, MPMBCreature.class);
        TemplateCreature creature = mpmbCreature.toTemplateCreature();
        return templateCreatureRepository.save(creature);
    }

    private CreatureSize getSize(int mpmbSize) {
        return switch (mpmbSize) {
            case 5 -> CreatureSize.TINY;
            case 4 -> CreatureSize.SMALL;
            case 3 -> CreatureSize.MEDIUM;
            case 2 -> CreatureSize.LARGE;
            case 1 -> CreatureSize.HUGE;
            case 0 -> CreatureSize.GARGANTUAN;
            default -> throw new IllegalArgumentException("Unexpected size " + mpmbSize);
        };
    }
}
