package com.example.planningproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    public EditText accessCode;
    Button btnLogIn;
    Vibrator vib;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        accessCode = findViewById(R.id.acessCode);
        btnLogIn = findViewById(R.id.btnLogIn);
        vib=(Vibrator)getSystemService(UserActivity.VIBRATOR_SERVICE);

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