package com.example.android.imageguess;

import android.content.Context;
import android.content.res.Resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Lars on 25.09.2017.
 */

public class Question {
    String Name;
    boolean answered;
    PictureResource res;


    String abc = "abcdefghijklmnopqrstuvwxyz";
    List<String> letters = new ArrayList<>();
    List<Integer> boxes = new ArrayList<>();
    Random random = new Random();









    public Question(Context context , int Fortschritt)
    {
        res = new PictureResource(Fortschritt,context);

        //Name = res.getResourceName(image);
        Name = res.Name;
        if(Name.length() >= 10){
            String s = Name;
            Name = s.substring(0,9);
        }
        answered = false;
        for( int i = 0; i< Name.length();i++)
        {
            letters.add(String.valueOf(Name.charAt(i)));

        }
        do
        {
            letters.add(Character.toString(abc.charAt(random.nextInt(26))));
        }while(letters.size()!=10);


        int temp = 0;
        do{
            temp = random.nextInt(10);
            if(!boxes.contains(temp)) {
                boxes.add(temp);
            }

        }
        while (boxes.size()<10);
    }

    public PictureResource getPictureResource(){
        return res;
    }
}
