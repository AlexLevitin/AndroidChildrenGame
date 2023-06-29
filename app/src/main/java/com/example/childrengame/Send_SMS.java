package com.example.childrengame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Send_SMS extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_sms);

        SharedPreferences sp = getSharedPreferences("ChildrenGameScore", MODE_PRIVATE);
        int savedValue = sp.getInt("key", 0);

        findViewById(R.id.SMSButton).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                EditText msgEdit=(EditText)findViewById(R.id.RecName);
                String msgStr= "Hi " + msgEdit.getText().toString() + ", I just scored " +savedValue +" points in Android Children Game!";
                EditText numEdit=(EditText)findViewById(R.id.PhoneNum);
                String phoneStr=numEdit.getText().toString();
                sendSMS( phoneStr,msgStr);
            }
            public void sendSMS(String phoneNo, String msg) {
                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNo, null, msg, null, null);
                    Toast.makeText(getApplicationContext(), "Message Sent",
                            Toast.LENGTH_LONG).show();
                } catch (Exception ex) {
                    Toast.makeText(getApplicationContext(),ex.getMessage().toString(),
                            Toast.LENGTH_LONG).show();
                    ex.printStackTrace();
                } }});
    }
}