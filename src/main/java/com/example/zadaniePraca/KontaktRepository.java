package com.example.zadaniePraca;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface KontaktRepository extends JpaRepository<Kontakt, Long> {
    Collection<Kontakt> findByOsoba(Osoba osoba);
    Optional<Kontakt> findByPhoneNumber(String phoneNumber);
    Optional<Kontakt> findByEmail(String email);
    List<Kontakt> findByEmailContains(String email);
}
