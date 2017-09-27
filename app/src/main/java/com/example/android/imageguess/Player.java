package com.example.android.imageguess;

/**
 * Created by android on 26.09.2017.
 */

public class Player {

    String Name;

    int Punkte;
    int Fortschritt;

    public Player(String _name ,int _fortschritt)
    {
        Name = _name;
        Fortschritt = _fortschritt;
    }

    public String getName(){
        return Name;
    }
    public int getFortschritt(){
        return Fortschritt;
    }
    public void addFortschritt(){
        Fortschritt++;
    }
}
