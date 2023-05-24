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

    public void SetControllers(int SentIndex)
    {
        //randomaly we choose the two options locations -random -1 or 2
        int randomNumber = new Random().nextInt(2)+1;
        Sentence.setText(SentencesOpt[SentIndex][0]);
        OptionOne.setText(SentencesOpt[SentIndex][randomNumber]);
        OptionTwo.setText(SentencesOpt[SentIndex][3-randomNumber]);
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
        //init the available options
        for (int i = 0; i < SentencesOpt.length; i++) {
            optionsList.add(i);
        }
        //init the first load
        SetRound();

        findViewById(R.id.Eitan_btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(OptionOne.getText().equals(SentencesOpt[randomNumber][3]))
                {
                    grade++;
                    v.setBackgroundResource(R.drawable.checkmarkv);
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