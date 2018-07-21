package com.example.zadaniePraca;

import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.constraints.AssertTrue;

import static org.junit.Assert.*;

public class SingletonDbControllerTest {


    @Test
    public void getInstance() {

         assertEquals(true,SingletonDbController.getInstance().isConntected());
    }
}