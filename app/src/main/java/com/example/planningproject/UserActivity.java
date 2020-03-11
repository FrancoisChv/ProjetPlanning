package com.example.planningproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class UserActivity extends AppCompatActivity {

    Button btnLogOut, btnGoApp;
    Vibrator vib;
    FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        vib=(Vibrator)getSystemService(MainActivity.VIBRATOR_SERVICE);
       // btnLogOut = (Button) findViewById(R.id.btnLogOut);
        btnGoApp = (Button) findViewById(R.id.btnGoApp);
      /*  btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                vib.vibrate(10);
                FirebaseAuth.getInstance().signOut();
                Intent I = new Intent(UserActivity.this, LoginActivity.class);
                startActivity(I);

            }
        }); */

        btnGoApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                vib.vibrate(10);
                FirebaseAuth.getInstance().signOut();
                Intent I = new Intent(UserActivity.this, ListEtudiantsActivity.class);
                startActivity(I);

            }
        });

    }
}
