package com.example.zadaniePraca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

@RestController
@RequestMapping("/kontakty/{name}")
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
    Collection<Kontakt> readContacts(@PathVariable String name) {
        Osoba osoba=osobaRepository.findByName(name).get();
        if(osoba==null) {

            System.out.println("No user found");

        }
        return this.kontaktRepository.findByOsoba(osoba);

    }

    @PostMapping
    ResponseEntity<?> add(@PathVariable String name, @ModelAttribute Kontakt input) {

        return this.osobaRepository
                .findByName(name)
                .map(account -> {
                    Kontakt result = this.kontaktRepository.save(new Kontakt(account,
                            input.getPhoneNumber(), input.getEmail()));
                    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
                })
                .orElse(ResponseEntity.noContent().build());
    }

    @DeleteMapping
    ResponseEntity<Object> delate(@PathVariable String name){
        Osoba osoba=osobaRepository.findByName(name).get();
       Iterator<Kontakt> it=kontaktRepository.findByOsoba(osoba).iterator();

        while (it.hasNext()){
            Kontakt k=it.next();
            kontaktRepository.delete(k);
        }
        return  ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }



    @PostMapping("/{old}/{variable}/{value}")
    ResponseEntity<Object> update(@PathVariable String old,@PathVariable String variable,@PathVariable String value,@PathVariable String name){
        Osoba osoba=osobaRepository.findByName(name).get();

        Iterator<Kontakt> it=kontaktRepository.findByOsoba(osoba).iterator();

        while (it.hasNext()){
            Kontakt k=it.next();

            switch (variable){
                case "phoneNumber":
                    Kontakt kontakt=k;
                    Kontakt kontakt2=new Kontakt(kontakt.getOsoba(),value,kontakt.getEmail());
                    kontaktRepository.delete(kontakt);
                    kontaktRepository.save(kontakt2);
                    break;
                case "email":
                    Kontakt kontakt3 = k;
                    Kontakt kontakt4 = new Kontakt(kontakt3.getOsoba(),kontakt3.getPhoneNumber(),value);
                    kontaktRepository.delete(kontakt3);
                    kontaktRepository.save(kontakt4);
                    break;
            }
        }


        return  ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
