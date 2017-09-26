package com.example.android.imageguess;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Lars on 25.09.2017.
 */

public class Question {
    String Name;
    boolean answered;

    List<String> letters = new ArrayList<>();
    List<Integer> boxes = new ArrayList<>();
    Random random = new Random();








    public Question(String _name)
    {
        Name = _name;

        for( int i = 0; i< Name.length();i++)
        {
            letters.add(String.valueOf(Name.charAt(i)));
            boxes.add(random.nextInt(Name.length()));
        }
    }
}
