package com.example.android.imageguess;

import android.content.Context;



class PictureResource {

    private int Image;
    String Name;
    private Context context;

    //Array mit allen Bilder-Ids
    private int[] img = {R.drawable.amazon,R.drawable.foerster,R.drawable.barbie,R.drawable.blockbuster,R.drawable.bmw,R.drawable.burgerking,
            R.drawable.canon,R.drawable.citroen,R.drawable.ebay,R.drawable.flickr,R.drawable.hp,R.drawable.ibm,R.drawable.kelloggs,
            R.drawable.levis,R.drawable.loreal,R.drawable.mcdonalds,R.drawable.michelin,R.drawable.microsoft,
            R.drawable.mtv,R.drawable.nescafe,R.drawable.nike,R.drawable.nissan,R.drawable.pizzahut,R.drawable.pringles,R.drawable.quicksilver,
            R.drawable.redbull,R.drawable.reebok,R.drawable.samsung,R.drawable.skype,R.drawable.starbucks,R.drawable.twitter,R.drawable.volkswagen};

    private int ImgReturn(int n)
    {
        return img[n];
    }

    PictureResource(int num, Context c){

        Image = ImgReturn(num);
        context = c;

        // gibt Namen von Bild zurück
        Name = c.getResources().getResourceEntryName(Image);
    }
    public String getName()
    {
        return Name;
    }
    int getImage(){
        return Image;
    }
    int getImageCount(){return img.length;}

}
