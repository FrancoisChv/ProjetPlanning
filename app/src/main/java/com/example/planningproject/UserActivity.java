package com.example.planningproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class UserActivity extends AppCompatActivity {

    Button btnGoListActivites, btnGoListEtudiants, btnGoListGroupes;
    Vibrator vib;
    FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        vib=(Vibrator)getSystemService(MainActivity.VIBRATOR_SERVICE);
        btnGoListActivites = (Button) findViewById(R.id.btnGoListActivites);
        btnGoListEtudiants = (Button) findViewById(R.id.btnGoListEtudiants);
        btnGoListGroupes = (Button) findViewById(R.id.btnGoListGroupes);

        btnGoListActivites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                vib.vibrate(10);
                FirebaseAuth.getInstance().signOut();
                Intent I = new Intent(UserActivity.this, ListActivitesActivity.class);
                startActivity(I);

            }
        });

        btnGoListEtudiants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                vib.vibrate(10);
                FirebaseAuth.getInstance().signOut();
                Intent I = new Intent(UserActivity.this, ListEtudiantsActivity.class);
                startActivity(I);

            }
        });

        btnGoListGroupes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                vib.vibrate(10);
                FirebaseAuth.getInstance().signOut();
                Intent I = new Intent(UserActivity.this, ListGroupesActivity.class);
                startActivity(I);

            }
        });

    }
}
