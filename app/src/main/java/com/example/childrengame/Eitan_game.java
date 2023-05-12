package com.example.childrengame;

import static java.lang.Thread.sleep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Eitan_game extends AppCompatActivity {

    String SentencesOpt[][] = {{"השמש ____ בלילה ואחותך תהיה איתי גם בלילה","תשקע","ישקע","תשקע"},{} };
    ArrayList<Integer> optionsList= new ArrayList<Integer>(Arrays.asList(0) );//,1,2,3,4,5,6,7,8,9,10,11,12,13,14
    ImageView v;
    ImageView x;
    TextView Sentence;
    Button OptionOne;
    Button OptionTwo;
    int randomNumber;
    int grade=0;
    int roundNumber=0;

    public void SetControllers(int SentIndex)
    {
        Sentence.setText(SentencesOpt[SentIndex][0]);
        OptionOne.setText(SentencesOpt[SentIndex][1]);
        OptionTwo.setText(SentencesOpt[SentIndex][2]);
    }

    public int SetRound()
    {
        if(optionsList.size()!=0&& !(roundNumber>=10)) {
            int randomNumberIndex = new Random().nextInt(optionsList.size());
            randomNumber=optionsList.get(randomNumberIndex);
            optionsList.remove(randomNumberIndex);
            SetControllers(randomNumber);
            roundNumber++;
            return randomNumber;
        }else
            return -1;
    }
    public void EndGame()
    {
        Intent HomePage =new Intent(Eitan_game.this,MainActivity.class);
        startActivity(HomePage);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eitan_game);
        Sentence=findViewById(R.id.Eitan_game_text);
        OptionOne=findViewById(R.id.Eitan_btn1);
        OptionTwo=findViewById(R.id.Eitan_btn2);
        v=findViewById(R.id.v_img);
        //init the first load
        SetRound();

        findViewById(R.id.Eitan_btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(OptionOne.getText().equals(SentencesOpt[randomNumber][3]))
                {
                    grade++;
                    v.setVisibility(View.VISIBLE);

                }
                else
                {
                    v.setBackgroundResource(R.drawable.xmark);
                    v.setVisibility(View.VISIBLE);



                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if(SetRound()==-1)
                {
                    EndGame();
                }



                }
            });
        findViewById(R.id.Eitan_btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(OptionTwo.getText().equals(SentencesOpt[randomNumber][3]))
                {
                    grade++;
                    v.setVisibility(View.VISIBLE);
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    if(SetRound()==-1)
                    {
                        EndGame();
                    }
                }
                else
                {
                    v.setBackgroundResource(R.drawable.xmark);
                    v.setVisibility(View.VISIBLE);

                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if(SetRound()==-1)
                {
                    EndGame();
                }



            }
        });






    }
}