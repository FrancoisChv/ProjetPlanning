package com.example.planningproject;

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
 * ListActiviteActivity est l'activite Android representant les activites du planning.
 *
 * @author CHAUVEAU Francois / OZTOPRAK David / REMAUD Remi / TASSI Kevin
 * @version 1.0
 */

public class ListActiviteActivity extends AppCompatActivity {

    private final String TAG = "ListActivity";
    DatabaseReference mDB;
    DatabaseReference mListItemRef;
    private RecyclerView mListItemsRecyclerView;
    private ListItemsAdapter mAdapter;
    private ArrayList<ListActivite> mMyListActivites;

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

        /* Reference a une base de donnees Firebase. */
        mDB= FirebaseDatabase.getInstance().getReference();

        /* Lien directe vers la table activites de la base de donnes Firebase. */
        mListItemRef = mDB.child("1fvb_V3xjrGD24-k2V3TAQJznydc9qQLjSzvt-kQGodg/activites");

        /* Creation de la liste des activites. */
        mMyListActivites = new ArrayList<>();

        /* Gestion de l'affichage de la liste. */
        mListItemsRecyclerView = (RecyclerView)findViewById(R.id.listItem_recycler_view);
        mListItemsRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(getResources()));
        mListItemsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        /* Mise a jour de la liste */
        updateUI();

        /* Détection d'une modification dans la base de donnees. */
        mListItemRef.addChildEventListener(new ChildEventListener() {

            @Override
            /* Si une nouvelle activite est ajoutee a la liste. */
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.d(TAG+"Added",dataSnapshot.getValue(ListActivite.class).toString());
                fetchData(dataSnapshot);
            }

            @Override
            /* Si une activitee a ete modifiee dans la liste. */
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Log.d(TAG+"Changed",dataSnapshot.getValue(ListActivite.class).toString());
            }

            @Override
            /* Si une activitee a ete supprimee de la liste. */
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.d(TAG+"Removed",dataSnapshot.getValue(ListActivite.class).toString());
            }

            @Override
            /* Si une activitee a ete changee de place dans la liste. */
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                Log.d(TAG+"Moved",dataSnapshot.getValue(ListActivite.class).toString());
            }

            @Override
            /* Si une activitee a ete supprimee d'une maniere non-autorisee de la liste. */
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG+"Cancelled",databaseError.toString());
            }
        });
    }

    /*Procedure recuperant les donnees issues de la liste des activites */
    private void fetchData(DataSnapshot dataSnapshot) {
        ListActivite listActivite =dataSnapshot.getValue(ListActivite.class);
        mMyListActivites.add(listActivite);
        updateUI();
    }

    /* Procedure permettant de mettre a jour la liste des activites des lors que l'utilisateur veut voir la liste.*/
    private void updateUI(){
        mAdapter = new ListItemsAdapter(mMyListActivites);
        mListItemsRecyclerView.setAdapter(mAdapter);
    }

    private class ListItemsHolder extends RecyclerView.ViewHolder{
        public TextView mNameTextView;
        public ListItemsHolder(View itemView){
            super(itemView);
            mNameTextView = (TextView) itemView.findViewById(R.id.textview_name);
        }

        /* Procedure permettant d'afficher la liste des activites. */
        public void bindData(ListActivite s){
            String test = s.getNom_Activite() +
                    "\n" + s.getNom_Salle() +
                    "\n" + s.getHoraires_Debut() +
                    "\n" + s.getHoraires_Fin();
            mNameTextView.setText(test);
        }
    }

    private class ListItemsAdapter extends RecyclerView.Adapter<ListItemsHolder>{
        private ArrayList<ListActivite> mListActivites;
        public ListItemsAdapter(ArrayList<ListActivite> listActivites){
            mListActivites = listActivites;
        }
        @Override
        public ListItemsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(ListActiviteActivity.this);
            View view = layoutInflater.inflate(R.layout.category_list_item_1,parent,false);
            return new ListItemsHolder(view);
        }

        @Override
        /* Procedure permettant de mettre a jour un element de la liste a une position donnee */
        public void onBindViewHolder(ListItemsHolder holder, int position) {
            ListActivite s = mListActivites.get(position);
            holder.bindData(s);
        }

        @Override
        /* Fonction retournant le nombre d'activites presentes la liste */
        public int getItemCount() {
            return mMyListActivites.size();
        }
    }
}