package com.example.planningproject;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * LoginActivity est l'activite Android representant la page de connexion a l'application.
 *
 * @author CHAUVEAU Francois / OZTOPRAK David / REMAUD Remi / TASSI Kevin
 * @version 1.0
 */

public class LoginActivity extends AppCompatActivity {

    public EditText accessCode;
    Button btnLogIn;
    Vibrator vib;
    public TextView log;
    private boolean result;

    @Override
    /* Creation de l'activite. */
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        /* Affichage en plein ecran de l'activite. */
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        /* Mis en place du layout de l'activite. */
        setContentView(R.layout.activity_login);

        result = false;

        /* Test du code d'acces saisie par l'utilisateur. */
        Intent I = getIntent();
        if (I.hasExtra("Resultat")) {
            result = true;
        }

        accessCode = findViewById(R.id.acessCode);
        btnLogIn = findViewById(R.id.btnLogIn);
        log = findViewById(R.id.resultLogin);
        vib=(Vibrator)getSystemService(UserActivity.VIBRATOR_SERVICE);

        /* Si le code d'acces est inconnu .*/
        if (result) {
            log.setText("Code d'accès incorrect !");
            log.setTextColor(Color.parseColor("#ee1010"));
        }

        /* Si l'utilisateur clique sur le bouton de connexion. */
        btnLogIn.setOnClickListener(new View.OnClickListener() {

            @Override
            /* Detection d'un clic. */
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

    /* Blocage du bouton retour */
    public void onBackPressed() {}

    @Override
    /* Permet de relancer l'activite si la saisie est incorrecte. */
    protected void onStart() {
        super.onStart();
    }
}