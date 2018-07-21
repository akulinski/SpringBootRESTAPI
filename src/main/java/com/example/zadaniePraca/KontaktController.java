package com.example.zadaniePraca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/kontakty/{osobaId}")
public class KontaktController {

    private final KontaktRepository kontaktRepository;
    private final OsobaRepository osobaRepository;

    @Autowired
    KontaktController(KontaktRepository kontaktRepository,
                           OsobaRepository osobaRepository) {
        this.kontaktRepository = kontaktRepository;
        this.osobaRepository = osobaRepository;
    }

    @GetMapping
    Collection<Kontakt> readContacts(@PathVariable String osobaId) {
        this.validateUser(osobaId);

        return this.kontaktRepository.findByOsoba(osobaId);
    }

    @PostMapping
    ResponseEntity<?> add(@PathVariable String userId, @RequestBody Kontakt input) {
        this.validateUser(userId);

        return this.osobaRepository
                .findByName(userId)
                .map(account -> {
                    Kontakt result = this.kontaktRepository.save(new Kontakt(account,
                            input.getphoneNumber(), input.getEmail()));

                    URI location = ServletUriComponentsBuilder
                            .fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(result.getId())
                            .toUri();

                    return ResponseEntity.created(location).build();
                })
                .orElse(ResponseEntity.noContent().build());
    }

    @GetMapping("/{kontaktId}")
    Optional<Kontakt> readBookmark(@PathVariable String userId, @PathVariable Long kontaktId) {
        this.validateUser(userId);

        return this.kontaktRepository
                .findById(kontaktId);

    }

    /**
     * Verify the {@literal userId} exists.
     *
     * @param userId
     */
    private void validateUser(String userId) {
        this.osobaRepository
                .findByName(userId);

    }

}
