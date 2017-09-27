package com.example.android.imageguess;

import android.content.Context;

/**
 * Created by android on 27.09.2017.
 */

public class PictureResource {

    int Image;
    String Name;
    Context context;

    private int[] img = {R.drawable.amazon,R.drawable.foerster,R.drawable.barbie,R.drawable.blockbuster,R.drawable.bmw,R.drawable.burgerking,
            R.drawable.canon,R.drawable.citroen,R.drawable.ebay,R.drawable.flickr,R.drawable.hp,R.drawable.ibm,R.drawable.kelloggs,
            R.drawable.levis,R.drawable.loreal,R.drawable.mcdonalds,R.drawable.michelin,R.drawable.microsoft,
            R.drawable.mtv,R.drawable.nescafe,R.drawable.nike,R.drawable.nissan,R.drawable.pizzahut,R.drawable.pringles,R.drawable.quicksilver,
            R.drawable.redbull,R.drawable.reebok,R.drawable.samsung,R.drawable.skype,R.drawable.starbucks,R.drawable.twitter,R.drawable.volkswagen};

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
    public int getImage(){
        return Image;
    }

}
