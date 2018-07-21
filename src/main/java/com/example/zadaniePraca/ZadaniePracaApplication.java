package com.example.zadaniePraca;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class ZadaniePracaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZadaniePracaApplication.class, args);
    }


    @Bean
    CommandLineRunner init(OsobaRepository osobaRepository,
                           KontaktRepository kontaktRepository) {
            return args ->
                    Arrays.asList("jhoeller","dsyer","pwebb","ogierke","rwinch","mfisher","mpollack","jlong")
                            .forEach(username -> {
                                Osoba account = osobaRepository.save(new Osoba(username, "password","3123321","1231231","a"));
                                kontaktRepository.save(new Kontakt(account, "http://bookmark.com/1/" + username, "A description"));
                                kontaktRepository.save(new Kontakt(account, "http://bookmark.com/2/" + username, "A description"));
                            });
    }
}
