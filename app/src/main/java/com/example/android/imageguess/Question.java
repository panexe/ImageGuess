package com.example.android.imageguess;

import android.content.Context;
import android.content.res.Resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;



class Question {

    String Name;
    private PictureResource res;

    // Alphabet
    private String abc;

    List<String> letters = new ArrayList<>();
    List<Integer> boxes = new ArrayList<>();
    private Random random = new Random();



    Question(Context context, int Fortschritt)
    {
        // Bild zur Frage
        res = new PictureResource(Fortschritt,context);

        abc = "abcdefghijklmnopqrstuvwxyz";
        Name = res.Name;

        // Begrenzt Namen auf 10 Zeichen
        if(Name.length() >= 10){
            String s = Name;
            Name = s.substring(0,9);
        }

        //Fügt Namen zum Buchstabenarray hinzu
        for( int i = 0; i< Name.length();i++)
        {
            letters.add(String.valueOf(Name.charAt(i)));

        }

        // Füllt Namen <10 Buchstaben mit zufälligen auf
        do
        {
            letters.add(Character.toString(abc.charAt(random.nextInt(26))));
        }while(letters.size()!=10);


        int temp;
        // Verteilt Buchstaben zufällig auf die 10 Buchstaben-Buttons
        do{
            temp = random.nextInt(10);
            if(!boxes.contains(temp)) {
                boxes.add(temp);
            }

        }
        while (boxes.size()<10);
    }

    PictureResource getPictureResource(){
        return res;
    }
}
