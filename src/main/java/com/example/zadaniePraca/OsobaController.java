package com.example.zadaniePraca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/osoba/{imie}")
public class OsobaController {


    private final KontaktRepository kontaktRepository;
    private final OsobaRepository osobaRepository;

    @Autowired
    OsobaController(KontaktRepository kontaktRepository,
                      OsobaRepository osobaRepository) {
        this.kontaktRepository = kontaktRepository;
        this.osobaRepository = osobaRepository;
    }

    @GetMapping
    Optional<Osoba> readAll(@PathVariable String imie) {
        return this.osobaRepository.findByName(imie);
    }

}
