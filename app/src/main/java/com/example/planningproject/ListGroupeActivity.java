package com.example.planningproject;

import android.content.Intent;
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
 * ListGroupeActivity est l'activite Android representant les groupes du planning.
 *
 * @author CHAUVEAU Francois / OZTOPRAK David / REMAUD Remi / TASSI Kevin
 * @version 1.0
 */

public class ListGroupeActivity extends AppCompatActivity {

    private final String TAG = "ListActivity";
    DatabaseReference mDB;
    DatabaseReference mListItemRef;
    private RecyclerView mListItemsRecyclerView;
    private ListItemsAdapter mAdapter;
    private ArrayList<ListGroupe> myListItems;
    private String IDgroupe;

    @Override
    /* Creation de l'activite. */
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        /* Affichage en plein ecran de l'activite. */
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        /* Mis en place du layout de l'activite. */
        setContentView(R.layout.activity_list_groupes);

        /* Test du groupe auquel appartient l'utilisateur. */
        Intent I = getIntent();
        if (I.hasExtra("idGroupe")) {
            IDgroupe = I.getStringExtra("idGroupe");
        }

        /* Reference a une base de donnees Firebase. */
        mDB= FirebaseDatabase.getInstance().getReference();

        /* Lien directe vers la table groupes de la base de donnes Firebase. */
        mListItemRef = mDB.child("1fvb_V3xjrGD24-k2V3TAQJznydc9qQLjSzvt-kQGodg/groupes");

        /* Creation de la liste des groupes. */
        myListItems = new ArrayList<>();

        /* Gestion de l'affichage de la liste. */
        mListItemsRecyclerView = (RecyclerView)findViewById(R.id.listItem_recycler_view);
        mListItemsRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(getResources()));
        mListItemsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        /* Mise a jour de la liste */
        updateUI();

        /* Détection d'une modification dans la base de donnees. */
        mListItemRef.addChildEventListener(new ChildEventListener() {

            @Override
            /* Si un nouveau groupe est ajoute a la liste. */
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.d(TAG+"Added",dataSnapshot.getValue(ListGroupe.class).toString());
                fetchData(dataSnapshot);
            }

            @Override
            /* Si un groupe a ete modifie dans la liste. */
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Log.d(TAG+"Changed",dataSnapshot.getValue(ListGroupe.class).toString());
            }

            @Override
            /* Si un groupe a ete supprime de la liste. */
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.d(TAG+"Removed",dataSnapshot.getValue(ListGroupe.class).toString());
            }

            @Override
            /* Si un groupe a ete change de place dans la liste. */
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                Log.d(TAG+"Moved",dataSnapshot.getValue(ListGroupe.class).toString());
            }

            @Override
            /* Si un groupe a ete supprime d'une maniere non-autorisee de la liste. */
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG+"Cancelled",databaseError.toString());
            }
        });
    }

    /*Procedure recuperant les donnees issues de la liste des groupes. */
    private void fetchData(DataSnapshot dataSnapshot) {
        ListGroupe listItem=dataSnapshot.getValue(ListGroupe.class);
        myListItems.add(listItem);
        updateUI();
    }

    /* Procedure permettant de mettre a jour la liste des groupes des lors que l'utilisateur veut voir la liste.*/
    private void updateUI(){
        mAdapter = new ListItemsAdapter(myListItems);
        mListItemsRecyclerView.setAdapter(mAdapter);
    }

    private class ListItemsHolder extends RecyclerView.ViewHolder{

        public TextView mNameTextView;

        public ListItemsHolder(View itemView){
            super(itemView);
            mNameTextView = (TextView) itemView.findViewById(R.id.textview_name);
        }

        /* Procedure permettant d'afficher la liste des groupes. */
        public void bindData(ListGroupe s){

            /* Si l'utilisateur appartient a un groupe etudiant. */
            if (s.getId_Groupe().equals(IDgroupe)) {
                String temp = s.getHoraires_Debut() + "\n" + s.getHoraires_Fin() + "\n" + s.getId_Groupe() + "\n" + s.getNom_Activite() ;
                mNameTextView.setText(temp);
            }

            /*Si l'utilisateur appartient a un groupe administrateur. */
            if (IDgroupe.equals("ADMIN")) {
                String temp = s.getHoraires_Debut() + "\n" + s.getHoraires_Fin() + "\n" + s.getId_Groupe() + "\n" + s.getNom_Activite() ;
                mNameTextView.setText(temp);
            }
        }
    }

    private class ListItemsAdapter extends RecyclerView.Adapter<ListItemsHolder>{

        private ArrayList<ListGroupe> mListItems;
        public ListItemsAdapter(ArrayList<ListGroupe> ListItems){
            mListItems = ListItems;
        }

        @Override
        public ListItemsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(ListGroupeActivity.this);
            View view = layoutInflater.inflate(R.layout.category_list_item_1,parent,false);
            return new ListItemsHolder(view);

        }

        @Override
        /* Procedure permettant de mettre a jour un element de la liste a une position donnee. */
        public void onBindViewHolder(ListItemsHolder holder, int position) {
            ListGroupe s = mListItems.get(position);
            holder.bindData(s);
        }

        @Override
        /* Fonction retournant le nombre de groupes presents la liste. */
        public int getItemCount() {
            return mListItems.size();
        }
    }
}