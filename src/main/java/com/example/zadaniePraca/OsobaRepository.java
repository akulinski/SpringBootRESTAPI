package com.example.zadaniePraca;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OsobaRepository extends JpaRepository<Osoba, Long> {
    Optional<Osoba> findByName(String nazwa);
}

