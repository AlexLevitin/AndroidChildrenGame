package com.example.childrengame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class Letters_complete extends AppCompatActivity {
    int randomNumber;
    int grade=0;
    int roundNumber=0;

    int[] pics = {R.drawable.chair, R.drawable.boy, R.drawable.cock, R.drawable.knight, R.drawable.shirt, R.drawable.socks
            , R.drawable.dog, R.drawable.cat, R.drawable.lion, R.drawable.fish, R.drawable.butterfly, R.drawable.bird
            , R.drawable.cow, R.drawable.horse, R.drawable.rabbit, R.drawable.elephant, R.drawable.bear, R.drawable.penguin
            , R.drawable.apple, R.drawable.cucumber, R.drawable.orange, R.drawable.banana, R.drawable.carrot, R.drawable.eggplant
            , R.drawable.squash, R.drawable.hammer, R.drawable.nail,  R.drawable.parcel, R.drawable.key, R.drawable.figure
            , R.drawable.table, R.drawable.cup, R.drawable.tree, R.drawable.flower, R.drawable.tooth, R.drawable.ear
            , R.drawable.eye, R.drawable.sun, R.drawable.moon, R.drawable.cloud, R.drawable.rain, R.drawable.snow, R.drawable.chalk
            , R.drawable.pilates, R.drawable.trampoline, R.drawable.collar, R.drawable.notebook, R.drawable.piano, R.drawable.food
            , R.drawable.book, R.drawable.television, R.drawable.telephone, R.drawable.north, R.drawable.south, R.drawable.east
            , R.drawable.west, R.drawable.sports, R.drawable.color, R.drawable.pool, R.drawable.playground, R.drawable.ball
            , R.drawable.track, R.drawable.beach, R.drawable.tower, R.drawable.counter, R.drawable.kitchen, R.drawable.song, R.drawable.family
            , R.drawable.party, R.drawable.icecream, R.drawable.station, R.drawable.music};
    String letters[][] = {
            {"כיסא", "chair"}, {"ילד", "boy"}, {"תרנגול", "cock"},
            {"אביר", "knight"}, {"חולצה", "shirt"}, {"גרביים", "socks"}, {"כלב", "dog"}, {"חתול", "cat"}, {"אריה", "lion"}, {"דג", "fish"}, {"פרפר", "butterfly"},
            {"ציפור", "bird"}, {"פרה", "cow"}, {"סוס", "horse"}, {"ארנב", "rabbit"}, {"פיל", "elephant"}, {"דוב", "bear"}, {"פינגווין", "penguin"}, {"תפוח", "apple"},
            {"מלפפון", "cucumber"}, {"תפוז", "orange"}, {"בננה", "banana"}, {"גזר", "carrot"}, {"חציל", "eggplant"}, {"קישוא", "squash"}, {"פטיש", "hammer"},
            {"מסמר", "nail"}, {"חבילה", "package"}, {"מפתח", "key"}, {"דמות", "figure"}, {"שולחן", "table"}, {"כוס", "cup"}, {"עץ", "tree"},
            {"פרח", "flower"},   {"שן", "tooth"}, {"אוזן", "ear"}, {"עין", "eye"}, {"שמש", "sun"}, {"ירח", "moon"},
            {"ענן", "cloud"}, {"גשם", "rain"}, {"שלג", "snow"}, {"גיר", "chalk"}, {"פילאטיס", "pilates"}, {"טרמפולינה", "trampoline"}, {"קולר", "collar"},
            {"מחברת", "notebook"}, {"פסנתר", "piano"},  {"אוכל", "food"}, {"ספר", "book"},  {"טלוויזיה", "television"},
            {"טלפון", "telephone"}, {"צפון", "north"}, {"דרום", "south"}, {"מזרח", "east"}, {"מערב", "west"}, {"ספורט", "sport"}, {"צבע", "color"},
            {"בריכה", "pool"}, {"מגרש משחק", "playground"}, {"כדור", "ball"}, {"מסלול", "track"}, {"חוף", "beach"}, {"מגדל", "tower"}, {"דלפק", "counter"},
            {"מטבח", "kitchen"}, {"שיר", "song"}, {"משפחה", "family"}, {"מסיבה", "party"}, {"גלידה", "ice cream"}, {"תחנה", "station"},{"מוזיקה", "music"}
    };
    ArrayList<Integer> optionsList= new ArrayList<Integer>( );
    TextView hebrewText;
    ImageView imgView;
    EditText AnswerEnglish;

    //after replacing letter , view should change
    public void SetController(int letterindex)
    {
        imgView.setBackground(getResources().getDrawable(pics[letterindex]));
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
        saveScore();
        finish();
    }
    public void saveScore()
    {
        SharedPreferences sp = getSharedPreferences("ChildrenGame", 0);
        SharedPreferences.Editor sedt = sp.edit();
        sedt.putInt("LettersScore", grade);
        sedt.commit();
        Toast.makeText(this, "You got: " + grade + " points", Toast.LENGTH_SHORT).show();


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
            if(randomNumber==-1) {
                EndGame();
                finish();
                return;
            }
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
        if(randomNumber==-1) {
            EndGame();
            finish();
            return;
        }
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
        if(randomNumber==-1) {
            EndGame();
            finish();
            return;
        }
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