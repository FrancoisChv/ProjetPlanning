package com.example.planningproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class UserActivity extends AppCompatActivity {

    Button btnGoListActivites, btnGoListEtudiants, btnGoListGroupes;
    private String idGroupe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Intent I = getIntent();
        if (I.hasExtra("idGroupe")) {
             idGroupe = I.getStringExtra("idGroupe");
        }
        
        btnGoListActivites = (Button) findViewById(R.id.btnGoListActivites);
        btnGoListEtudiants = (Button) findViewById(R.id.btnGoListEtudiants);
        btnGoListGroupes = (Button) findViewById(R.id.btnGoListGroupes);

        btnGoListActivites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent I = new Intent(UserActivity.this, ListActivitesActivity.class);
                startActivity(I);

            }
        });

        btnGoListEtudiants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent I = new Intent(UserActivity.this, ListEtudiantsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("idGroupe", idGroupe);
                I.putExtras(bundle);
                startActivity(I);

            }
        });

        btnGoListGroupes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent I = new Intent(UserActivity.this, ListGroupesActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("idGroupe", idGroupe);
                I.putExtras(bundle);
                startActivity(I);

            }
        });
    }
}
