package com.example.childrengame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class Letters_complete extends AppCompatActivity {
    int randomNumber;
    int grade=0;
    int roundNumber=0;
    String images[];
    String letters[][] = {
            {"כיסא", "chair"}, {"ילד", "boy"}, {"תרנגול", "cock"},
            {"אביר", "knight"}, {"חולצה", "shirt"}, {"גרביים", "socks"}, {"כלב", "dog"}, {"חתול", "cat"}, {"אריה", "lion"}, {"דג", "fish"}, {"פרפר", "butterfly"},
            {"ציפור", "bird"}, {"פרה", "cow"}, {"סוס", "horse"}, {"ארנב", "rabbit"}, {"פיל", "elephant"}, {"דוב", "bear"}, {"פינגווין", "penguin"}, {"תפוח", "apple"},
            {"מלפפון", "cucumber"}, {"תפוז", "orange"}, {"בננה", "banana"}, {"גזר", "carrot"}, {"חציל", "eggplant"}, {"קישוא", "squash"}, {"פטיש", "hammer"},
            {"מסמר", "nail"}, {"פזמון", "rhyme"}, {"חבילה", "package"}, {"מפתח", "key"}, {"דמות", "figure"}, {"שולחן", "table"}, {"כוס", "cup"}, {"עץ", "tree"},
            {"פרח", "flower"}, {"אורן", "pine"}, {"בטן", "belly"}, {"שן", "tooth"}, {"אוזן", "ear"}, {"עין", "eye"}, {"שמש", "sun"}, {"ירח", "moon"},
            {"ענן", "cloud"}, {"גשם", "rain"}, {"שלג", "snow"}, {"טיח", "chalk"}, {"פילאטיס", "pilates"}, {"טרמפולינה", "trampoline"}, {"קולר", "collar"},
            {"מחברת", "notebook"}, {"פסנתר", "piano"}, {"קולות", "sounds"}, {"אוכל", "food"}, {"ספר", "book"}, {"שולחן", "table"}, {"טלוויזיה", "television"},
            {"טלפון", "telephone"}, {"צפון", "north"}, {"דרום", "south"}, {"מזרח", "east"}, {"מערב", "west"}, {"ספורט", "sport"}, {"צבע", "color"},
            {"בריכה", "pool"}, {"מגרש משחק", "playground"}, {"כדור", "ball"}, {"מסלול", "track"}, {"חוף", "beach"}, {"מגדל", "tower"}, {"דלפק", "counter"},
            {"מטבח", "kitchen"}, {"שיר", "song"}, {"משפחה", "family"}, {"אורחים", "guests"}, {"גלידה", "ice cream"}, {"תחנה", "station"},{"מוזיקה", "music"}
    };
    ArrayList<Integer> optionsList= new ArrayList<Integer>( );
    TextView hebrewText;
    ImageView imgView;
    EditText AnswerEnglish;

    //after replacing letter , view should change
    public void SetController(int letterindex)
    {
        //imgView.setBackground(); change image
        hebrewText.setText(letters[letterindex][0]);
        AnswerEnglish.setText("");
    }
    //for controlling on non repeating letters,we removed letter that we see ,if all done return flag
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
        Intent HomePage =new Intent(Letters_complete.this,MainActivity.class);
        startActivity(HomePage);
    }
    private void submitText(String text) {
        String ans=text.toLowerCase();
        if(letters[randomNumber][1].equals(ans))
        {
            // in case you right
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
            AnswerEnglish.setText("");
            AnswerEnglish.setTypeface(Typeface.DEFAULT);
            randomNumber=RandNumber();
            if(randomNumber==-1)
                EndGame();
            SetController(randomNumber);


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
        if(randomNumber==-1)
            EndGame();
        SetController(randomNumber);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letters_complete);
        //init the available colors to use
        for (int i = 0; i < letters.length; i++) {
            optionsList.add(i);
        }
        hebrewText=findViewById(R.id.letters_complete_hebrew);
        imgView = findViewById(R.id.color_complete_img);
        AnswerEnglish=findViewById(R.id.answer_text_letters);
        randomNumber=RandNumber();
        if(randomNumber==-1)
            EndGame();
        SetController(randomNumber);
        AnswerEnglish.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // Check if the Enter key is pressed
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP) {
                    // Perform the submission logic here
                    String text = AnswerEnglish.getText().toString();
                    submitText(text);
                    return true;
                }
                return false;
            }
        });

    }

}