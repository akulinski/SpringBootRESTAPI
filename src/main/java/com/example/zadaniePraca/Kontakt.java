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

    private String phoneber;

    private String email;
    private Kontakt(){}

    public Kontakt(Osoba osoba, String phoneNumber, String email) {
        this.osoba = osoba;
        this.phoneber = phoneNumber;
        this.email = email;
    }

    public static Kontakt from(Osoba account, Kontakt bookmark) {
        return new Kontakt(account, bookmark.getphoneNumber(), bookmark.getEmail());
    }

    public Long getId() {
        return id;
    }

    public Osoba getOsoba() {
        return osoba;
    }

    public String getphoneNumber() {
        return phoneber;
    }

    public String getEmail() {
        return email;
    }
}
