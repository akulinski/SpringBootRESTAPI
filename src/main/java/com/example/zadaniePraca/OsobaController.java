package com.example.zadaniePraca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/osoba")
public class OsobaController {


    private final KontaktRepository kontaktRepository;
    private final OsobaRepository osobaRepository;

    @Autowired
    OsobaController(KontaktRepository kontaktRepository,
                    OsobaRepository osobaRepository) {
        this.kontaktRepository = kontaktRepository;
        this.osobaRepository = osobaRepository;
    }

    @GetMapping("/{imie}")
    Optional<Osoba> readAll(@PathVariable String imie) {
        return this.osobaRepository.findByName(imie);
    }

    @PostMapping
    ResponseEntity<Object> add(@ModelAttribute Osoba input){

        Osoba osoba=new Osoba(input.getName(),input.getSecondName(),input.getDateOfBirth(),input.getPesel(),input.getGender());
        osobaRepository.save(osoba);
        System.out.println(osoba.getName()+osoba.getSecondName());
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @DeleteMapping("/{name}")
    ResponseEntity<Object> delate(@PathVariable String name){
        osobaRepository.delete(osobaRepository.findByName(name).get());
        return  ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PostMapping("/{name}/{variable}/{value}")
    ResponseEntity<Object> update(@PathVariable String name,@PathVariable String variable,@PathVariable String value){
        Osoba osoba = osobaRepository.findByName(name).get();
        Osoba osobaTmp=new Osoba(osoba.getName(),osoba.getSecondName(),osoba.getDateOfBirth(),osoba.getPesel(),osoba.getGender());

        switch (variable){
            case "name":
                osobaRepository.delete(osoba);
                osobaTmp.setName(value);
                osobaRepository.save(osobaTmp);
                break;
            case "secondName":
                osobaRepository.delete(osoba);

                osobaTmp.setSecondName(value);
                osobaRepository.save(osobaTmp);
                break;
            case "dateOfBirth":
                osobaRepository.delete(osoba);
                osobaTmp.setDateOfBirth(value);
                osobaRepository.save(osobaTmp);
                break;
            case "pesel":
                osobaRepository.delete(osoba);
                osobaTmp.setPesel(value);
                osobaRepository.save(osobaTmp);
                break;
            case "gender":
                osobaRepository.delete(osoba);
                osobaTmp.setGender(value);
                osobaRepository.save(osobaTmp);
                break;

            default:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return  ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}
