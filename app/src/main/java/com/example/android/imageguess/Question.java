package com.example.android.imageguess;

import java.util.ArrayList;
import java.util.Arrays;
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




   private int[] img = {R.drawable.Amazon,R.drawable.Barbie,R.drawable.Blockbuster,R.drawable.BMW,R.drawable.BurgerKing,
          R.drawable.Canon,R.drawable.Citroen,R.drawable.Ebay,R.drawable.Flickr,R.drawable.HP,R.drawable.IBM};
    private int randint = random.nextInt(img.length);

    String name;



    public int ImgReturn()
    {
        return img[randint];



    }







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
