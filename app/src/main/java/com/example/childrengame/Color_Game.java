package com.example.childrengame;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;




public class Color_Game extends AppCompatActivity {
    boolean FirstTime=false;
    int randomNumber;
    int grade=0;
    int roundNumber=0;
    int BackgroundColors[] = {0xFFFF0000, 0xFF008000, 0xFF0000FF, 0xFFFFFF00, 0xFFFFA500, 0xFFFFC0CB, 0xFF800080, 0xFFFFFFFF, 0xFF000000, 0xFF808080, 0xFF964B00, 0xFF40E0D0, 0xFF00FFFF, 0xFFFFD700, 0xFFC0C0C0, 0xFF00FFFF};
    String HebrewColors[]={"אדום" ,"ירוק","כחול","צהוב","כתום","ורוד","סגול", "לבן" , "שחור" , "אפור" , "חום" , "טורקיז" , "אקווה" , "זהב", "כסף"};
    String EnglishColors[]={"red" ,"green","blue","yellow","orange","pink","purple", "white" , "black" , "grey" , "brown" , "turquoise" , "aqua" , "gold", "silver"};
    ArrayList<Integer> optionsList= new ArrayList<Integer>( );

    TextView hebrewText;
    TextView ColorBackground;
    EditText AnswerEnglish;


    //after replacing color , view should change
    public void SetController(int color)
    {
        ColorBackground.setBackgroundColor(BackgroundColors[color]);
        hebrewText.setText(HebrewColors[color]);
    }
    //for controlling on non repeating colors,we removed color that we see ,if all done return flag
    public int RandNumber()
    {
        if(optionsList.size()!=0&& !(roundNumber>=10)) {
            int randomNumberIndex = new Random().nextInt(optionsList.size());
            randomNumber=optionsList.get(randomNumberIndex);
            optionsList.remove(randomNumberIndex);
            roundNumber++;
            return randomNumber;
        }else
            return -1;
    }
    public void EndGame()
    {
        Intent HomePage =new Intent(Color_Game.this,MainActivity.class);
        startActivity(HomePage);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //red ,green,blue,yellow,orange,pink,purple,white,black,grey,brown,turquoise,aqua,gold,silver
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_game);
        //init the available colors to use
        for (int i = 0; i < BackgroundColors.length; i++) {
            optionsList.add(i);
        }
        hebrewText=findViewById(R.id.color_hebrew);
        ColorBackground=findViewById(R.id.color_game_img);
        AnswerEnglish=findViewById(R.id.answer_text_color);
        //if we already was at the game and just rotate the screen , we need to restore the game
        if(savedInstanceState!=null)
        {
            randomNumber = savedInstanceState.getInt("randomNumber");
            FirstTime=savedInstanceState.getBoolean("FirstTime");

            if(savedInstanceState.containsKey("optionsList"))
                optionsList=savedInstanceState.getIntegerArrayList("optionsList");

        }
        else
        {
    //        optionsList=new ArrayList<Integer>(Arrays.asList(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14) );
        }

        if(!FirstTime)
        {
            randomNumber=RandNumber();
            if(randomNumber==-1) {
                EndGame();
                finish();
                return;
            }
            SetController(randomNumber);
            FirstTime=true;
        }

        findViewById(R.id.Color_Btn_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ans=AnswerEnglish.getText().toString().toLowerCase();
                if(EnglishColors[randomNumber].equals(ans))
                {// in case you right
                    grade++;
                    try {
                        //put it in green when answer is right
                        AnswerEnglish.setBackgroundColor(Color.parseColor("#5FEF37"));
                        AnswerEnglish.setText("you right!");
                        AnswerEnglish.setTypeface(null, Typeface.BOLD);
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                AnswerEnglish.setBackgroundColor(Color.TRANSPARENT);
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        AnswerEnglish.setBackgroundColor(Color.parseColor("#5FEF37"));
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                AnswerEnglish.setBackgroundColor(Color.TRANSPARENT);
                                            }
                                        }, 100);
                                    }
                                }, 100);
                            }
                        }, 100);


                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }else//in case you faild
                {
                    AnswerEnglish.setBackgroundColor(Color.parseColor("#FF0000"));
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            AnswerEnglish.setBackgroundColor(Color.TRANSPARENT);
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    AnswerEnglish.setBackgroundColor(Color.parseColor("#FF0000"));
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            AnswerEnglish.setBackgroundColor(Color.TRANSPARENT);
                                        }
                                    }, 150);
                                }
                            }, 150);
                        }
                    }, 150);
                }
                AnswerEnglish.setText("");
                AnswerEnglish.setTypeface(Typeface.DEFAULT);
                randomNumber=RandNumber();
                if(randomNumber==-1) {
                    EndGame();
                    finish();
                    return;
                }
                SetController(randomNumber);




            }
        });






    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save the ArrayList to the instance state
        outState.putIntegerArrayList("optionsList", optionsList);
    }
}