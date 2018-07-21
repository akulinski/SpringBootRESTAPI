package com.example.zadaniePraca;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class OsobaTest {
    private Osoba osoba=null;
    @Before
    public void initializ(){
       osoba=new Osoba("Albert","Kulinski","1995-06-17","59595959595","m");
    }

    @Test
    public void getDateOfBirth() {

        assertEquals("1995-06-17",osoba.getDateOfBirth());
    }

    @Test
    public void getGender() {
        assertEquals("m",osoba.getGender());
    }

    @Test
    public void getName() {
        assertEquals("Albert",osoba.getName());
    }

    @Test
    public void getSecondName() {

        assertEquals("Kulinski",osoba.getSecondName());
    }

    @Test
    public void getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date convertedCurrentDate = null;
        try {
            convertedCurrentDate = sdf.parse("1918-01-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        assertNotEquals(convertedCurrentDate,osoba.getDate());
    }
}