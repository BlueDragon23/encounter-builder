package dev.aidang.encounters.rest;

import dev.aidang.encounters.NotFoundException;
import dev.aidang.encounters.model.creatures.TemplateCreature;
import dev.aidang.encounters.repository.TemplateCreatureRepository;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TemplateCreatureController {

    private final TemplateCreatureRepository templateCreatureRepository;

    public TemplateCreatureController(TemplateCreatureRepository templateCreatureRepository) {
        this.templateCreatureRepository = templateCreatureRepository;
    }

    @GetMapping("/monsters")
    public Page<TemplateCreature> getMonsters(Pageable pageable) {
        return templateCreatureRepository.findAll(pageable);
    }

    @GetMapping("/monsters/{id}")
    public TemplateCreature getMonster(Long id) {
        Optional<TemplateCreature> creature = templateCreatureRepository.findById(id);
        if (creature.isEmpty()) {
            throw new NotFoundException("No monster exists with ID %d".formatted(id));
        }
        return creature.get();
    }
}
