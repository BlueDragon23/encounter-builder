package dev.aidang.encounters.service;

import static dev.aidang.encounters.Utils.JSON;

import com.fasterxml.jackson.core.type.TypeReference;
import dev.aidang.encounters.model.creatures.*;
import dev.aidang.encounters.repository.TemplateCreatureRepository;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class TemplateCreatureService {

    private final TemplateCreatureRepository templateCreatureRepository;

    public TemplateCreatureService(TemplateCreatureRepository templateCreatureRepository) {
        this.templateCreatureRepository = templateCreatureRepository;
    }

    @PostConstruct
    private void bulkImport() {
        try (InputStream inputStream = TemplateCreatureService.class.getResourceAsStream("/5e-SRD-Monsters.json")) {
            List<FifthEditionDatabaseCreature> creatures = JSON.readValue(inputStream, new TypeReference<>() {});
            templateCreatureRepository.saveAll(creatures.stream()
                    .map(FifthEditionDatabaseCreature::toTemplateCreature)
                    .toList());
        } catch (IOException e) {
            throw new RuntimeException("Failed to import creatures from SRD", e);
        }
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
