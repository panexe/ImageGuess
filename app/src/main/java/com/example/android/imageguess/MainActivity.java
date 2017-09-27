package com.example.android.imageguess;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

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


    LinearLayout LL_ViewButtons;

    Question currentQuestion;
    ImageView Anzeige;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();

        for(int i = 0; i< 10; i++)
        {
            button_views.get(i).setOnClickListener(clickListener);
            button_letters.get(i).setOnClickListener(clickListener);
        }
        button_views.get(0).setOnClickListener((clickListener));

        for (int i = 0; i < button_pressed.length; i++) {
            button_pressed[i] = false;
            button_view_pressed[i] = false;
        }

        nextQuestion(new Question("sivic"));

    }



    private void initComponents()

    {
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

        LL_ViewButtons.setWeightSum(10);


    }

    private OnClickListener clickListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            for (int i = 0; i < 10; i++) {
                // EventHandler Eingabe Buttons
                if (view == button_letters.get(i))
                {
                    if(button_pressed[i] == false) {
                        // Setzt Letter Button gedrückt
                        button_pressed[i] = true;
                        //Setzt anzeigebutton auf gedrückt
                        button_view_pressed[button_views.indexOf(getFirstEmptyView())] = true;
                        // Setzt ersten freien Anzeigebutton auf text von Letter Button
                        button_assosiation[button_views.indexOf(getFirstEmptyView())] = i;

                        getFirstEmptyView().setText(button_letters.get(i).getText());

                        button_letters.get(i).setText("");

                        if(checkSolution()){
                            nextQuestion(new Question("foerster"));
                        }

                    }else{}
                }

                //Eventhandler Anzeige Buttons
                if (button_views.get(i) == view)
                {
                    // Falls AnzeigeButton nicht gedrückt
                    if(button_view_pressed[i] == false) {
                        // auf gedrückt gesetzt
                        //button_view_pressed[i] = true;
                        // Anzeigebutton text leeren
                        //button_views.get(i).setText("");
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
                // Eventhandler InfoButton
                if(button_infos.get(0)== view)
                {
                    //evntl. Buy Hint
                }


            }
        }
    };

    private boolean checkSolution(){
        String solution = "";
        for(int i = 0; i<currentQuestion.Name.length();i++) {
            solution += button_views.get(i).getText();
        }
        if(solution.toUpperCase() == currentQuestion.Name.toUpperCase())
        {
            return true;
        }
        return false;
    }


    private Button getFirstEmptyView()
    {
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
        currentQuestion = _question;
        // Setzen der Button-Anzahl
        LL_ViewButtons.setWeightSum(_question.Name.length());

        // Weißt jedem Button einen buchstaben zu
        for(int i = 0;i<_question.letters.size();i++)
        {
            // Letter Button (nmr :frage.boxen(i)) auf
            button_letters.get((Integer)_question.boxes.get(i)).setText(_question.letters.get(i));

        }
        // Legt das neue Bild fest
        Anzeige.setImageResource(_question.ImgReturn());
        //

    }

}





