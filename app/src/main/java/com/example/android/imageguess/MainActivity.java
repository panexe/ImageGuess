package com.example.android.imageguess;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static android.view.View.*;

public class MainActivity extends AppCompatActivity {

    List<Button> button_views = new ArrayList<>();
    List<Button> button_letters = new ArrayList<>();
    List<Button> button_infos = new ArrayList<>();
    Boolean[] button_pressed = new Boolean[10];
    Boolean[] button_view_pressed = new Boolean[10];
    Integer[] button_assosiation = new Integer[10];
    Boolean firstQuestion;
    Player player;

    SharedPreferences sp;
    Context context;
    LinearLayout LL_ViewButtons;

    Question currentQuestion;
    ImageView Anzeige;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialisiert Bedienelemente
        initComponents();

        //speicherung:
        sp = getSharedPreferences("your_prefs", MODE_PRIVATE);

        // läd gespeicherte werte in den player
        player = new Player("Emil",sp.getInt("key_fortschritt",MODE_PRIVATE),sp.getInt("key_punkte",MODE_PRIVATE));

        context = this.getApplicationContext();

        // weißt eventhandler zu
        for(int i = 0; i< 10; i++)
        {
            button_views.get(i).setOnClickListener(clickListener);
            button_letters.get(i).setOnClickListener(clickListener);
        }
        button_infos.get(0).setOnClickListener(clickListener);
        button_infos.get(1).setOnClickListener((clickListener));

        //init button_arrays
        for (int i = 0; i < button_pressed.length; i++) {
            button_pressed[i] = false;
            button_view_pressed[i] = false;
        }


        firstQuestion = true;


        // ruft die erste Frage auf
        nextQuestion(new Question(context,player.getFortschritt()));


    }

    @Override
    protected void onStop(){
        super.onStop();

        // Speicherung über SharedPreferences
        sp = getSharedPreferences("your_prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("key_fortschritt", player.getFortschritt());
        editor.putInt("key_punkte",player.getPunkte());
        editor.commit();

    }



    private void initComponents()

    {
        // Initialisieren von allen Bedienelementen
        button_views.add((Button) findViewById(R.id.button_view_0));
        button_views.add((Button) findViewById(R.id.button_view_1));
        button_views.add((Button) findViewById(R.id.button_view_2));
        button_views.add((Button) findViewById(R.id.button_view_3));
        button_views.add((Button) findViewById(R.id.button_view_4));
        button_views.add((Button) findViewById(R.id.button_view_5));
        button_views.add((Button) findViewById(R.id.button_view_6));
        button_views.add((Button) findViewById(R.id.button_view_7));
        button_views.add((Button) findViewById(R.id.button_view_8));
        button_views.add((Button) findViewById(R.id.button_view_9));


        button_letters.add((Button) findViewById(R.id.button_letter_0));
        button_letters.add((Button) findViewById(R.id.button_letter_1));
        button_letters.add((Button) findViewById(R.id.button_letter_2));
        button_letters.add((Button) findViewById(R.id.button_letter_3));
        button_letters.add((Button) findViewById(R.id.button_letter_4));
        button_letters.add((Button) findViewById(R.id.button_letter_5));
        button_letters.add((Button) findViewById(R.id.button_letter_6));
        button_letters.add((Button) findViewById(R.id.button_letter_7));
        button_letters.add((Button) findViewById(R.id.button_letter_8));
        button_letters.add((Button) findViewById(R.id.button_letter_9));


        button_infos.add((Button) findViewById(R.id.button_info_0));
        button_infos.add((Button) findViewById(R.id.button_info_1));

        Anzeige = (ImageView) findViewById(R.id.imageview);

        LL_ViewButtons = (LinearLayout) findViewById(R.id.LinearLayout_ViewButtons);


    }

    private OnClickListener clickListener = new OnClickListener() {
        @Override
        public void onClick(View view) {

            //eventhandler infobutton 2
            if(view == button_infos.get(0))
            {
                // Zeigt  Punkteanzahl
                Toast.makeText(context, "Punkte: "+player.getPunkte(), Toast.LENGTH_SHORT).show();
            }

            // Eventhandler InfoButton
            if(button_infos.get(1)== view)
            {
                // Zeigt Fragenanzahl
                Toast.makeText(context,player.getFortschritt()+1 +"/"+ currentQuestion.getPictureResource().getImageCount(),Toast.LENGTH_SHORT).show();
            }

            for (int i = 0; i < 10; i++) {

                // EventHandler Eingabe Buttons
                if (view == button_letters.get(i))
                {

                    if(button_views.indexOf(getFirstEmptyView()) < currentQuestion.Name.length()) {


                    if (!button_pressed[i]) {
                        // Setzt Letter Button gedrückt
                        button_pressed[i] = true;
                        //Setzt anzeigebutton auf gedrückt
                        button_view_pressed[button_views.indexOf(getFirstEmptyView())] = true;
                        // Setzt ersten freien Anzeigebutton auf text von Letter Button
                        button_assosiation[button_views.indexOf(getFirstEmptyView())] = i;

                        try {
                            getFirstEmptyView().setText(button_letters.get(i).getText());
                        }catch(NullPointerException ex){
                            Toast.makeText(context, ex.getMessage(),Toast.LENGTH_SHORT).show();
                        }

                        button_letters.get(i).setText("");

                        if (checkSolution()) {
                            nextQuestion(new Question(context, player.getFortschritt()));
                        }


                    }
                }
                }

                //Eventhandler Anzeige Buttons
                if (button_views.get(i) == view)
                {
                    // Falls AnzeigeButton nicht gedrückt
                    if(!button_view_pressed[i]) {
                       // nicht passiert
                    }else{
                        // Setzt Anzeige Button als gedrückt
                        button_view_pressed[i] = false;
                        // Setzt Letter Button als ungedrückt
                        //button_pressed[currentQuestion.boxes.get(i)] = false;
                        button_pressed[button_assosiation[i]] = false;
                        // Setzt entsprechenden Letter Button Text zu dem vom View Button
                        button_letters.get(button_assosiation[i]).setText(button_views.get(i).getText());
                        button_views.get(i).setText("");


                    }

                }



            }
        }
    };

    private boolean checkSolution(){
        String solution = "";

        // Fügt die buchstaben zu einem string zusammen
        for(int i = 0; i<currentQuestion.Name.length();i++) {
            solution += button_views.get(i).getText();
        }


        try {

            if(solution.toUpperCase().equals(currentQuestion.Name.toUpperCase())){
                // bei Übereinfstimmung :
                player.addPunkte();

                if(player.getFortschritt()+2 > (currentQuestion.getPictureResource().getImageCount())){
                    player.resetFortschritt();
                }
                else{
                    player.addFortschritt();
                }

                return true;

            }else{
                return false;
            }
        }catch (Exception ex){
            return false;

        }
    }


    private Button getFirstEmptyView()
    {
        // Gibt ersten nicht Benutzten AnzeigeButton zurück
        for (int i = 0 ;i < 10;i++)
        {
            if(button_views.get(i).getText() == "")
            {
                return button_views.get(i);
            }


        }
        return null;
    }

    public void nextQuestion(Question _question)
    {
        // Schreibt Punkte in Info-Button
        int Punkte = player.getPunkte();
        button_infos.get(0).setText(Integer.toString(Punkte));

        // setzt bool-arrays der buttons zurück
        for(int i = 0 ; i< 10;i++){
            button_views.get(i).setText("");
            button_letters.get(i).setText("");
            button_view_pressed[i] = false;
            button_pressed[i] = false;
        }
        // setzt neue question
        currentQuestion = _question;
        // Setzen der Button-Anzahl
        LL_ViewButtons.setWeightSum(_question.Name.length());

        // Weißt jedem Button einen buchstaben zu
        for(int i = 0;i<_question.letters.size();i++)
        {
            // Letter Button (nmr :frage.boxen(i)) auf
            button_letters.get(_question.boxes.get(i)).setText(_question.letters.get(i));

        }

        // Zeigt nächstes Bild an
        Anzeige.setImageResource(_question.getPictureResource().getImage());


    }

}





