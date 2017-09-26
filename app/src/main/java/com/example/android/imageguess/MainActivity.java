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

    LinearLayout LL_ViewButtons;

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
                    getFirstEmptyView().setText(button_letters.get(i).getText());

                }

                //Eventhandler Anzeige Buttons
                if (button_letters.get(i) == view)
                {
                    button_letters.get(i).setText("hallo");
                    LL_ViewButtons.setWeightSum(3);
                }
                // Eventhandler InfoButton
                if(button_infos.get(0)== view)
                {
                    //evntl. Buy Hint
                }


            }
        }
    };


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
        LL_ViewButtons.setWeightSum(_question.Name.length());

        for(int i = 0;i<_question.Name.length();i++)
        {
            button_letters.get(i).setText(_question.letters.get((Integer)_question.boxes.get(i)));
        }
        }

    }





