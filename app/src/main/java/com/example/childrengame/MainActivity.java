package com.example.childrengame;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
public class MainActivity extends AppCompatActivity {

    TextView scoreView;
    int totalScore;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sp = getSharedPreferences("ChildrenGameScore", MODE_PRIVATE);
        SharedPreferences.Editor sedt = sp.edit();
        sedt.putInt("key",0);
        scoreView=findViewById(R.id.total_score);

        loadScore();


        //connecting button click to memory game
        findViewById(R.id.ButtonMemory).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent memoryGameGo =new Intent(MainActivity.this,MemoryGame.class);
                startActivity(memoryGameGo);

            }
        });
        //connecting button click to word_complete game
        findViewById(R.id.buttonWords).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Word_complete =new Intent(MainActivity.this,Letters_complete.class);
                startActivity(Word_complete);

            }
        });
        //connecting button click to color game
        findViewById(R.id.buttonColors).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent color_game =new Intent(MainActivity.this,Color_Game.class);
                startActivity(color_game);

            }
        });
        //connecting button click to Eitan game
        findViewById(R.id.buttonEitan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Eitan_game =new Intent(MainActivity.this,Eitan_game.class);
                startActivity(Eitan_game);

            }
        });

        //connecting button click to sens SMS
        findViewById(R.id.SmsButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Send_SMS =new Intent(MainActivity.this,Send_SMS.class);
                startActivity(Send_SMS);

            }
        });

        findViewById(R.id.VidButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent VidAc =new Intent(MainActivity.this,Video.class);
                startActivity(VidAc);

            }
        });

    }

    public void loadScore()
    {
        SharedPreferences sp = getSharedPreferences("ChildrenGameScore", 0);
        //totalScore = sp.getInt("key", 0) + sp.getInt("EitanScore", 0) + sp.getInt("LettersScore", 0);
        totalScore=sp.getInt("key",0);
        scoreView.setText("Total Score: " + totalScore);
    }
}