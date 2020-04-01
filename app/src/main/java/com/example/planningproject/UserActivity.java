package com.example.planningproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

/**
 * UserActivity est l'activite Android representant la page d'accueil ou arrive l'utilisateur apres la connexion.
 *
 * @author CHAUVEAU Francois / OZTOPRAK David / REMAUD Remi / TASSI Kevin
 * @version 1.0
 */

public class UserActivity extends AppCompatActivity {

    Button btnGoListActivites, btnGoListEtudiants, btnGoListGroupes, btnGoListCodes;
    private String idGroupe;

    @Override
    /* Creation de l'activite. */
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        /* Affichage en plein ecran de l'activite. */
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        /* Mis en place du layout de l'activite. */
        setContentView(R.layout.activity_user);

        idGroupe = "";

        /* Test du groupe auquel appartient l'utilisateur. */
        Intent I = getIntent();
        if (I.hasExtra("idGroupe")) {
             idGroupe = I.getStringExtra("idGroupe");
        } else {
            idGroupe = "ADMIN";
        }

        btnGoListActivites = (Button) findViewById(R.id.btnGoListActivites);
        btnGoListEtudiants = (Button) findViewById(R.id.btnGoListEtudiants);
        btnGoListGroupes = (Button) findViewById(R.id.btnGoListGroupes);

        /* Si l'utilisateur clique sur le bouton des activites. */
        btnGoListActivites.setOnClickListener(new View.OnClickListener() {

            @Override
            /* Detection d'un clic. */
            public void onClick(View view) {

                Intent I = new Intent(UserActivity.this, ListActiviteActivity.class);
                startActivity(I);
            }
        });

        /* Si l'utilisateur clique sur le bouton des etudiants. */
        btnGoListEtudiants.setOnClickListener(new View.OnClickListener() {

            @Override
            /* Detection d'un clic. */
            public void onClick(View view) {

                Intent I = new Intent(UserActivity.this, ListEtudiantActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("idGroupe", idGroupe);
                I.putExtras(bundle);
                startActivity(I);
            }
        });

        /* Si l'utilisateur clique sur le bouton des groupes. */
        btnGoListGroupes.setOnClickListener(new View.OnClickListener() {

            @Override
            /* Detection d'un clic. */
            public void onClick(View view) {

                Intent I = new Intent(UserActivity.this, ListGroupeActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("idGroupe", idGroupe);
                I.putExtras(bundle);
                startActivity(I);
            }
        });
    }

    /* Blocage du bouton retour */
    public void onBackPressed() {}
}
