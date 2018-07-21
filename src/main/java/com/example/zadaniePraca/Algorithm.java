package com.example.zadaniePraca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.LinkedList;

@RestController
@RequestMapping("/algorithm")
public class Algorithm {

    private final KontaktRepository kontaktRepository;
    private final OsobaRepository osobaRepository;

    @Autowired
    Algorithm(KontaktRepository kontaktRepository,
                      OsobaRepository osobaRepository) {
        this.kontaktRepository = kontaktRepository;
        this.osobaRepository = osobaRepository;
    }

    @GetMapping("/{email}")
    LinkedList<Osoba> findPeopleByEmail(@PathVariable String email) {
        LinkedList<Osoba> lista=new LinkedList<>();
        LinkedList<Kontakt> kotankty = new LinkedList<>();

        if(email.startsWith("*")&&email.endsWith("*")){
            Iterator<Kontakt> it = kontaktRepository.findByEmailContains(email.substring(1,email.length()-1)).iterator();

            while (it.hasNext()){
                lista.add(it.next().getOsoba());
            }
        }
        else {
            Kontakt kontakt = kontaktRepository.findByEmail(email).get();
            lista.add(kontakt.getOsoba());
        }
        return lista;
    }


    @GetMapping("/date/{date1}/{date2}")
    LinkedList<Osoba> findPeopleByBirthDateBetween(@PathVariable String date1,@PathVariable String date2){
        return osobaRepository.findByDateOfBirthIsBetween(date1,date2);
    }

}
