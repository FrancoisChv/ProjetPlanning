package com.example.planningproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * ListCodeActivity est l'activite Android representant les codes du planning.
 *
 * @author CHAUVEAU Francois / OZTOPRAK David / REMAUD Remi / TASSI Kevin
 * @version 1.0
 */

public class ListCodeActivity extends AppCompatActivity {

    private final String TAG = "ListActivity";
    DatabaseReference mDB;
    DatabaseReference mListItemRef;
    private RecyclerView mListItemsRecyclerView;
    private ListItemsAdapter mAdapter;
    private ArrayList<ListCode> mMyListCodes;
    private String str1;
    private TextView test;
    private Boolean trouve = false;

    @Override
    /* Creation de l'activite. */
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        /* Affichage en plein ecran de l'activite. */
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        /* Mis en place du layout de l'activite. */
        setContentView(R.layout.activity_list_items);

        /* Variable permettant de tester la validite du code d'acces. */
        test = findViewById(R.id.test);

        /* Test du code d'acces entre par l'utilisateur. */
        Intent I = getIntent();
        if (I.hasExtra("code")) {
            str1 = I.getStringExtra("code");
        }

        /* Reference a une base de donnees Firebase. */
        mDB= FirebaseDatabase.getInstance().getReference();

        /* Lien directe vers la table codes de la base de donnes Firebase. */
        mListItemRef = mDB.child("1fvb_V3xjrGD24-k2V3TAQJznydc9qQLjSzvt-kQGodg/codes");

        /* Creation de la liste des codes. */
        mMyListCodes = new ArrayList<>();

        /* Gestion de l'affichage de la liste. */
        mListItemsRecyclerView = (RecyclerView)findViewById(R.id.listItem_recycler_view);
        mListItemsRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(getResources()));
        mListItemsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        /* Mise a jour de la liste */
        updateUI();

        /* Détection d'une modification dans la base de donnees. */
        mListItemRef.addChildEventListener(new ChildEventListener() {

            @Override
            /* Si un nouveau code d'acces est ajoute a la liste. */
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.d(TAG+"Added",dataSnapshot.getValue(ListCode.class).toString());
                fetchData(dataSnapshot);
            }

            @Override
            /* Si un code a ete modifie dans la liste. */
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Log.d(TAG+"Changed",dataSnapshot.getValue(ListCode.class).toString());
            }

            @Override
            /* Si un code a ete supprime de la liste. */
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.d(TAG+"Removed",dataSnapshot.getValue(ListCode.class).toString());
            }

            @Override
            /* Si un code a ete change de place dans la liste. */
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                Log.d(TAG+"Moved",dataSnapshot.getValue(ListCode.class).toString());
            }

            @Override
            /* Si un code a ete supprime d'une maniere non-autorisee de la liste. */
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG+"Cancelled",databaseError.toString());
            }
        });
    }

    /*Procedure recuperant les donnees issues de la liste des codes. */
    private void fetchData(DataSnapshot dataSnapshot) {
        ListCode listCode =dataSnapshot.getValue(ListCode.class);
        mMyListCodes.add(listCode);
        updateUI();
    }

    /* Procedure permettant de mettre a jour la liste des codes des lors que l'utilisateur veut voir la liste.*/
    private void updateUI(){
        mAdapter = new ListItemsAdapter(mMyListCodes);
        mListItemsRecyclerView.setAdapter(mAdapter);
    }

    private class ListItemsHolder extends RecyclerView.ViewHolder{

        public TextView mNameTextView;

        public ListItemsHolder(View itemView){
            super(itemView);
            mNameTextView = (TextView) itemView.findViewById(R.id.textview_name);
        }

        /* Procedure permettant d'afficher la liste des codes. */
        public void bindData(ListCode s){
            String result = "";

            /* Si l'utilisateur appartient a un groupe etudiant. */
            if (s.getCode_Acces().equals(str1)) {
                String id_groupe = s.getId_Groupe();
                trouve = true;
                Intent I = new Intent(ListCodeActivity.this, UserActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("idGroupe", id_groupe);
                I.putExtras(bundle);
                startActivity(I);

            /*Si l'utilisateur appartient a un groupe administrateur. */
            } else if (str1.equals("ADMIN")) {
                result = s.getId_Groupe() + "\n" + s.getCode_Acces();
                Intent I = new Intent(ListCodeActivity.this, UserActivity.class);
                startActivity(I);
                mNameTextView.setText(result);
            }

            /* Si l'utilisateur n'appartient a aucun groupe de la liste. */
            if (!trouve) {
                String access = "Refus";
                Intent I = new Intent(ListCodeActivity.this, LoginActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("Resultat", access);
                I.putExtras(bundle);
                startActivity(I);
            }
        }
    }

    private class ListItemsAdapter extends RecyclerView.Adapter<ListItemsHolder>{

        private ArrayList<ListCode> mListCodes;
        public ListItemsAdapter(ArrayList<ListCode> listCodes){
            mListCodes = listCodes;
        }

        @Override
        public ListItemsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(ListCodeActivity.this);
            View view = layoutInflater.inflate(R.layout.category_list_item_1,parent,false);
            return new ListItemsHolder(view);
        }

        @Override
        /* Procedure permettant de mettre a jour un element de la liste a une position donnee. */
        public void onBindViewHolder(ListItemsHolder holder, int position) {
            ListCode s = mListCodes.get(position);
            holder.bindData(s);
        }

        @Override
        /* Fonction retournant le nombre de codes presents la liste. */
        public int getItemCount() {
            return mMyListCodes.size();
        }
    }

}