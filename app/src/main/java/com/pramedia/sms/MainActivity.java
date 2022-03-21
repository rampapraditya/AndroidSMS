package com.pramedia.sms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText txtPhone, txtMessage;
    private Button btnKirim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtPhone = findViewById(R.id.txtPhone);
        txtMessage = findViewById(R.id.txtMessage);

        btnKirim = findViewById(R.id.btnKirim);
        btnKirim.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == btnKirim){
            SmsManager smsMan = SmsManager.getDefault();

            if(Build.VERSION.SDK_INT >= 23){
                if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{ Manifest.permission.SEND_SMS}, 100);
                }else{
                    smsMan.sendTextMessage(txtPhone.getText().toString(), null, txtMessage.getText().toString(), null, null);
                }
            }else{
                smsMan.sendTextMessage(txtPhone.getText().toString(), null, txtMessage.getText().toString(), null, null);
            }
        }
    }
}