package dev.aidang.encounters.rest;

import dev.aidang.encounters.NotFoundException;
import dev.aidang.encounters.model.Encounter;
import dev.aidang.encounters.repository.EncounterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.StreamSupport;

@RestController
public class EncounterController {

    private final EncounterRepository encounterRepository;

    @Autowired
    public EncounterController(EncounterRepository encounterRepository) {
        this.encounterRepository = encounterRepository;
    }

    @GetMapping("/encounter/{name}")
    public Encounter getEncounter(@PathVariable("name") String encounterName) {
        return StreamSupport
                .stream(encounterRepository
                        .findAll()
                        .spliterator(), true)
                .filter(e -> e
                        .name()
                        .equals(encounterName))
                .findAny()
                .orElseThrow(() -> new NotFoundException("Failed to find encounter " + encounterName));
    }

    @PostMapping("/encounter")
    public Encounter createEncounter(@RequestBody Encounter encounter) {
        return encounterRepository.save(encounter);
    }
}
