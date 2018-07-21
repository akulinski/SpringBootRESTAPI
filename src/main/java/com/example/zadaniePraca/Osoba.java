package com.example.zadaniePraca;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Osoba {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String secondName;

    private String dateOfBirth;

    private String pesel;

    private String gender;

    @OneToMany(mappedBy = "Osoba")
    private Set<Kontakt> kontakty = new HashSet<>();

    private Osoba() { }

    public Osoba(final String name, final String secondName,final String dateOfBirth, final  String pesel,final String gender) {
        this.name = name;
        this.secondName = secondName;
        this.dateOfBirth = dateOfBirth;
        this.pesel = pesel;
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSecondName() {
        return secondName;
    }

    public Set<Kontakt> getKontakty() {
        return kontakty;
    }

}
