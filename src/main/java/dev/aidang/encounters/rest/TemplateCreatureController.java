package dev.aidang.encounters.rest;

import dev.aidang.encounters.model.creatures.TemplateCreature;
import dev.aidang.encounters.model.creatures.TemplateCreatureSummary;
import dev.aidang.encounters.service.TemplateCreatureService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TemplateCreatureController {

    private final TemplateCreatureService templateCreatureService;

    public TemplateCreatureController(TemplateCreatureService templateCreatureService) {
        this.templateCreatureService = templateCreatureService;
    }

    @GetMapping("/monsters")
    public Page<TemplateCreatureSummary> getMonsters(
            Pageable pageable, @RequestParam(value = "name", required = false) String name) {
        return templateCreatureService.searchMonsters(pageable, name);
    }

    @GetMapping("/monsters/{id}")
    public TemplateCreature getMonster(@PathVariable("id") Long id) {
        return templateCreatureService.getMonster(id);
    }
}
