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
                    Arrays.asList("Konrad","Tomasz","Alek")
                            .forEach(username -> {
                                Osoba account = osobaRepository.save(new Osoba(username, "test","1995-06-17","01234567891","m"));
                                kontaktRepository.save(new Kontakt(account, "1232131" + username+" "+RandomStringGenerator.getInstance().getString(5), "email@gmail"));
                                kontaktRepository.save(new Kontakt(account, "123123123432" + username+" "+RandomStringGenerator.getInstance().getString(5), "email2@interia"));
                            });
    }
}
