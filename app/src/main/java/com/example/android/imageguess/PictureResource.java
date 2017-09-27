package com.example.android.imageguess;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by android on 27.09.2017.
 */

public class PictureResource {

    int Image;
    String Name;
    Context context;

    private int[] img = {R.drawable.amazon,R.drawable.barbie,R.drawable.blockbuster,R.drawable.bmw,R.drawable.burgerking,
            R.drawable.canon,R.drawable.citroen,R.drawable.ebay,R.drawable.flickr,R.drawable.hp,R.drawable.ibm};

    public int ImgReturn(int n)
    {
        return img[n];
    }

    public PictureResource(int num , Context c){

        Image = ImgReturn(num);

        context = c;
        Name = c.getResources().getResourceEntryName(Image);
        //Name = "test";



    }
    public String getName()
    {
        return Name;
    }

}
