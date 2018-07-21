package com.example.zadaniePraca;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class KontaktTest {
    @Mock
    Kontakt kontakt;
    @Mock
    Osoba osoba;

    @Before
    public void initalize(){
        osoba=new Osoba("Albert","Kulinski","1995-06-17","59595959595","m");
        kontakt=new Kontakt(osoba,"970211111","albert.albert@albert.com");
    }

    @Test
    public void getOsoba() {

       assertEquals(kontakt.getOsoba(),osoba);
    }

    @Test
    public void getPhoneNumber() {

        assertEquals(kontakt.getPhoneNumber(),"970211111");
    }

    @Test
    public void getEmail() {
        assertEquals(kontakt.getEmail(),"albert.albert@albert.com");
    }
}