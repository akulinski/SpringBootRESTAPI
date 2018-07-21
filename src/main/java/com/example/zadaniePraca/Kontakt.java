package com.example.zadaniePraca;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Kontakt {

    @Id
    @GeneratedValue
    private Long id;

    @JsonIgnore
    @ManyToOne
    private Osoba osoba;

    private String phoneNumer;

    private String email;

    private Kontakt() { } // JPA only

    private Kontakt(Osoba osoba, String phoneNumer, String email) {
        this.osoba = osoba;
        this.phoneNumer = phoneNumer;
        this.email = email;
    }

    public static Kontakt from(Osoba account, Kontakt bookmark) {
        return new Kontakt(account, bookmark.getPhoneNumer(), bookmark.getEmail());
    }

    public Long getId() {
        return id;
    }

    public Osoba getOsoba() {
        return osoba;
    }

    public String getPhoneNumer() {
        return phoneNumer;
    }

    public String getEmail() {
        return email;
    }
}
