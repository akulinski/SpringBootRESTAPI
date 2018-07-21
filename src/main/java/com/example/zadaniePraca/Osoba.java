package com.example.zadaniePraca;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Osoba {

    @Id
    @GeneratedValue
    private Long id;

    public void setName(String name) {
        this.name = name;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    private String name;

    private String secondName;

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    private String dateOfBirth;

    public String getPesel() {
        return pesel;
    }

    public String getGender() {
        return gender;
    }

    private String pesel;

    private String gender;

    @OneToMany(mappedBy = "osoba")
    private Set<Kontakt> kontakty = new HashSet<>();

    private Osoba() { }

    public Osoba(final String name, final String secondName,final String dateOfBirth, final  String pesel,final String gender) {
        this.name = name;
        this.secondName = secondName;
        this.dateOfBirth=dateOfBirth;
        this.pesel=pesel;
        this.gender=gender;
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

    public Date getDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date convertedCurrentDate = null;
        try {
            convertedCurrentDate = sdf.parse(dateOfBirth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return convertedCurrentDate;
    }

}
