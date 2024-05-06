package dev.aidang.encounters.rest;

import dev.aidang.encounters.NotFoundException;
import dev.aidang.encounters.model.Encounter;
import dev.aidang.encounters.model.EncounterSummary;
import dev.aidang.encounters.repository.EncounterRepository;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EncounterController {

    private final EncounterRepository encounterRepository;

    @Autowired
    public EncounterController(EncounterRepository encounterRepository) {
        this.encounterRepository = encounterRepository;
    }

    @GetMapping("/encounters")
    public Page<EncounterSummary> getEncounters(Pageable pageable) {
        return encounterRepository.findAllProjectedBy(pageable);
    }

    @GetMapping("/encounters/{id}")
    public Encounter getEncounter(@PathVariable("id") Long id) {
        return encounterRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Encounter with ID=%d does not exist".formatted(id)));
    }

    @PostMapping("/encounters")
    public ResponseEntity<Encounter> createEncounter(@RequestBody Encounter encounter) {
        return ResponseEntity.created(URI.create("/encounter/" + encounter.name()))
                .body(encounterRepository.save(encounter));
    }
}
