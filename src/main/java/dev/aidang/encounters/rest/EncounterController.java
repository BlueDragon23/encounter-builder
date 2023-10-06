package dev.aidang.encounters.rest;

import dev.aidang.encounters.NotFoundException;
import dev.aidang.encounters.model.Encounter;
import dev.aidang.encounters.repository.EncounterRepository;
import java.net.URI;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EncounterController {

    private final EncounterRepository encounterRepository;

    @Autowired
    public EncounterController(EncounterRepository encounterRepository) {
        this.encounterRepository = encounterRepository;
    }

    @GetMapping("/encounter/{name}")
    public Encounter getEncounter(@PathVariable("name") String encounterName) {
        return StreamSupport.stream(encounterRepository.findAll().spliterator(), true)
                .filter(e -> e.name().equals(encounterName))
                .findAny()
                .orElseThrow(() -> new NotFoundException("Failed to find encounter " + encounterName));
    }

    @PostMapping("/encounter")
    public ResponseEntity<Encounter> createEncounter(@RequestBody Encounter encounter) {
        return ResponseEntity.created(URI.create("/encounter/" + encounter.name()))
                .body(encounterRepository.save(encounter));
    }
}
