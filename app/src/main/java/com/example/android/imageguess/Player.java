package com.example.android.imageguess;


class Player {


    private String Name;

    private int Punkte;
    private int Fortschritt;

    Player(String _name, int _fortschritt, int _punkte)
    {
        Punkte = _punkte;
        Name = _name;
        Fortschritt = _fortschritt;
    }

    public String getName(){
        return Name;
    }
    int getFortschritt(){
        return Fortschritt;
    }
    void addFortschritt(){
        Fortschritt++;
    }
    void addPunkte(){Punkte += 100;}
    int getPunkte(){return Punkte;}
}
