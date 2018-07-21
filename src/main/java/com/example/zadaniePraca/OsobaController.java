package com.example.zadaniePraca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    String add(@ModelAttribute Osoba input){

        Osoba osoba=new Osoba(input.getName(),input.getSecondName(),input.getDateOfBirth(),input.getPesel(),input.getGender());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date convertedCurrentDate = null;
        try {
            convertedCurrentDate = sdf.parse("1918-01-01");
        } catch (ParseException e) {
            e.printStackTrace();
            return "Wrong Date";
        }
        Date now=new Date();
        if(osoba.getDate().after(now)||osoba.getDate().before(convertedCurrentDate)){
            return "Wrong Date";
        }
        else if(!checkPesel(osoba.getPesel())){
            return "Wrong Pesel";
        }

        else if(osoba.getName().equals("")||osoba.getSecondName().equals("")){
            return "Empty Name or Second Name";
        }
        else {
            osobaRepository.save(osoba);
            System.out.println(osoba.getName() + osoba.getSecondName());
            return "User added";
        }
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

    boolean checkPesel(String pesel){
        Pattern VALID_EMAIL_ADDRESS_REGEX =
                Pattern.compile("^[0-9]{11}", Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(pesel);
        return matcher.find();
    }


}
