package com.example.planningproject;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.data.model.User;

public class LoginActivity extends AppCompatActivity {

    public EditText accessCode;
    Button btnLogIn;
    Vibrator vib;
    public TextView log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        accessCode = findViewById(R.id.acessCode);
        btnLogIn = findViewById(R.id.btnLogIn);
        log = findViewById(R.id.resultlogin);
        vib=(Vibrator)getSystemService(UserActivity.VIBRATOR_SERVICE);

        Intent I = getIntent();
        if (I.hasExtra("Resultat")) {
           log.setText("Code d'accès incorrect !");
           log.setTextColor(Color.parseColor("#ee1010"));
        }



        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent I = new Intent(LoginActivity.this, ListCodeActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("code", String.valueOf(accessCode.getText()));
                I.putExtras(bundle);
                startActivity(I);
                vib.vibrate(10);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}