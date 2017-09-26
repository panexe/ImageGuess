package com.example.android.imageguess;


import android.widget.ImageView;

import com.example.android.imageguess.R;


import java.util.Random;

/**
 * Created by Lars on 25.09.2017.
 */

public class Question {
    private Random rand = new Random();
    private int[] img = {R.drawable.images,R.drawable.images1};
    private int randint = rand.nextInt(img.length);



    public int ImgReturn()
    {
        return img[randint];
    }



}
