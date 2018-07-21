package com.example.zadaniePraca;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface KontaktRepository extends JpaRepository<Kontakt, Long> {
    Collection<Kontakt> findByOsoba(String username);
}
