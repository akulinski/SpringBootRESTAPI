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

    private String phoneNumber;

    private String email;
    private Kontakt(){}

    public Kontakt(Osoba osoba, String phoneNumber, String email) {
        this.osoba = osoba;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public static Kontakt from(Osoba account, Kontakt bookmark) {
        return new Kontakt(account, bookmark.getPhoneNumber(), bookmark.getEmail());
    }

    public Long getId() {
        return id;
    }

    public Osoba getOsoba() {
        return osoba;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }


}
