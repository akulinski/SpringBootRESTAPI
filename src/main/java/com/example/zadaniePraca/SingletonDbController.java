package com.example.zadaniePraca;

public class SingletonDbController {

    private static SingletonDbController instance=null;

    SingletonDbController(){
        instance=new SingletonDbController();
    }

    
}
