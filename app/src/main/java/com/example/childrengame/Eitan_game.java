package com.example.childrengame;

import static java.lang.Thread.sleep;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Eitan_game extends AppCompatActivity {

    String SentencesOpt[][] = {{"בבוקר אנחנו __ לעבודה", "נקום", "תקום", "נקום"},
            {"אחרי השיעור אני __ להתאמן", "אלך", "ילך", "אלך"},
            {"אתם __ לסבתא בערב ?", "תבואו", "יבואו", "תבואו"},
            {"אנחנו __ מחר לצפון", "נסע", "אסע", "נסע"},
            {"מחר __ יין", "אשתה", "ישתה", "אשתה"},
            {"אתה __ תזמין פיצה עם זיתים בערב ?", "תזמין", "נזמין", "תזמין"},
            {"הוא __ לשיעור מחר אל תדאג", "יבוא", "תבוא", "יבוא"},
            {"היא __ את העציצים בבוקר", "תשקה", "נשקה", "תשקה"},
            {"אנחנו __ מחר למסיבה", "נצא", "יצאו", "נצא"},
            {"התינוק __ חלב לפני השינה", "ינק", "אנק", "ינק"},
            {"__ את ההודעה כשאתפנה", "אשמע", "ישמע", "אשמע"},
            {"אנחנו __ יותר מאוחר אליכם", "נצטרף", "תצטרף", "נצטרף"},
            {"אתה __ מאוחר יותר ?", "תתקשר", "אתקשר", "תתקשר"},
            {"אני __ אותך ב20 בערב", "אאסוף", "יאסוף", "אאסוף"},
            {"את __ מוכנה בזמן ?", "תהיי", "אהיה", "תהיי"},
            {"אלכס ודויד __ את שאר העבודה", "יעשו", "תעשו", "יעשו"},
            {"דור ולינוי __ בסוף השנה", "יתחתנו", "נתחתן", "יתחתנו"},
            {"יוסי __ את הדיג׳י לחתונה של דור", "יסגור", "נסגור", "יסגור"},
            {"יובל ורועי __ מהצבא בסוף השנה", "ישתחררו", "תשתחררו", "ישתחררו"},
            {"הם __ חוגר בקרוב", "יגזרו", "נגזור", "יגזרו"},
            {"אז __ תור לעוד שבועיים ?", "נקבע", "יקבע", "נקבע"},
            {"הרופא __ מרשם לטיפול", "יתן", "תתן", "יתן"},
            {"הילדים __ ליומולדת של נועה", "יוזמנו", "תוזמנו", "יוזמנו"},
            {"אנחנו __ את העבודה בהצלחה", "נסיים", "תסיים", "נסיים"},
            {"בקרוב אנו __ את התואר", "נסיים", "יסיימו", "נסיים"},
            {"כש__ גדולה", "אהיה", "יהיה", "אהיה"},
            {"אנחנו __ לציונים בעבודה", "נחכה", "אחכה", "נחכה"},
            {"כולנו __ עבודה בסוף", "נמצא", "אמצא", "נמצא"} };
    ArrayList<Integer> optionsList= new ArrayList<Integer>( );
    ImageView v;
    ImageView x;
    TextView Sentence;
    Button OptionOne;
    Button OptionTwo;
    int randomNumber;
    int grade=0;
    int roundNumber=0;

    // the function set on the controller the values and the answer , the location of the right ans change any time randomly
    public void SetControllers(int SentIndex)
    {
        //randomaly we choose the two options locations -random -1 or 2
        int randomNumber = new Random().nextInt(2)+1;
        Sentence.setText(SentencesOpt[SentIndex][0]);
        OptionOne.setText(SentencesOpt[SentIndex][randomNumber]);
        OptionTwo.setText(SentencesOpt[SentIndex][3-randomNumber]);
    }

    //take randomal number from the available one ,and set it and the controllers
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
    //end game and return to the menu
    public void EndGame()
    {
        Intent HomePage =new Intent(Eitan_game.this,MainActivity.class);
        saveScore();
        startActivity(HomePage);
    }

    public void saveScore()
    {
        SharedPreferences sp = getSharedPreferences("ChildrenGameScore", MODE_PRIVATE);
        int savedValue = sp.getInt("key", 0);
        SharedPreferences.Editor sedt = sp.edit();
        sedt.putInt("key", savedValue+grade);
        sedt.commit();
        Toast.makeText(this, "You got: " + grade + " points", Toast.LENGTH_SHORT).show();

    }
    //this animation should show after every press , its fades in and then out , and then we skip to the next question/end game
    private void startFadeAnimation() {

        // Fade in animation
        ObjectAnimator fadeInAnimator = ObjectAnimator.ofFloat(v, "alpha", 0f, 1f);
        fadeInAnimator.setDuration(1000);
        fadeInAnimator.setInterpolator(new AccelerateInterpolator());

        // Fade out animation
        ObjectAnimator fadeOutAnimator = ObjectAnimator.ofFloat(v, "alpha", 1f, 0f);
        fadeOutAnimator.setDuration(1000);
        fadeOutAnimator.setInterpolator(new AccelerateInterpolator());

        // Chain the animations
        fadeInAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                // After the fade in animation ends, start the fade out animation
                fadeOutAnimator.start();
            }
        });

        fadeOutAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                // After the fade out animation ends, hide the image
                v.setVisibility(View.GONE);
                if(SetRound()==-1)
                {
                    EndGame();
                }
            }
        });

        // Start the fade in animation
        fadeInAnimator.start();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eitan_game);
        Sentence=findViewById(R.id.Eitan_game_text);
        OptionOne=findViewById(R.id.Eitan_btn1);
        OptionTwo=findViewById(R.id.Eitan_btn2);
        v=findViewById(R.id.v_img);
        //init the available options
        for (int i = 0; i < SentencesOpt.length; i++) {
            optionsList.add(i);
        }
        //init the first load
        SetRound();

        //in case of click on btn1
        findViewById(R.id.Eitan_btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View z) {
                if(OptionOne.getText().equals(SentencesOpt[randomNumber][3]))
                {
                    //its true give a grade and show v
                    grade++;
                    v.setBackgroundResource(R.drawable.checkmarkvv);
                    v.setVisibility(View.VISIBLE);
                    startFadeAnimation();

                }
                else
                {
                    //its false show x
                    v.setBackgroundResource(R.drawable.xmark);
                    v.setVisibility(View.VISIBLE);
                    startFadeAnimation();


                }
            }
            });
        //in case of click on btn2
        findViewById(R.id.Eitan_btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View z) {
                if(OptionTwo.getText().equals(SentencesOpt[randomNumber][3]))
                {
                    //its true give a grade and show v
                    grade++;
                    v.setBackgroundResource(R.drawable.checkmarkvv);
                    v.setVisibility(View.VISIBLE);
                    startFadeAnimation();


                }
                else
                {
                    //its false show x
                    v.setBackgroundResource(R.drawable.xmark);
                    v.setVisibility(View.VISIBLE);
                    startFadeAnimation();

                }




            }

        });


    }

}