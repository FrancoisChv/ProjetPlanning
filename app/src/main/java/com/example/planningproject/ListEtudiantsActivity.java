package com.example.planningproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class ListEtudiantsActivity extends AppCompatActivity {

    private final String TAG = "ListActivity";
    DatabaseReference mDB;
    DatabaseReference mListItemRef;
    private RecyclerView mListItemsRecyclerView;
    private ListItemsAdapter mAdapter;
    private ArrayList<ListEtudiant> mMyListEtudiants;
    private String IDgroupe;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_items);

        Intent I = getIntent();
        if (I.hasExtra("idGroupe")) {
            IDgroupe = I.getStringExtra("idGroupe");
        }

        mDB= FirebaseDatabase.getInstance().getReference();
        mListItemRef = mDB.child("1fvb_V3xjrGD24-k2V3TAQJznydc9qQLjSzvt-kQGodg/etudiants");
        mMyListEtudiants = new ArrayList<>();
        mListItemsRecyclerView = (RecyclerView)findViewById(R.id.listItem_recycler_view);
        mListItemsRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(getResources()));
        mListItemsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        updateUI();

        /* Permet l'affichage A GARDER */
        mListItemRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.d(TAG+"Added",dataSnapshot.getValue(ListEtudiant.class).toString());
                fetchData(dataSnapshot);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Log.d(TAG+"Changed",dataSnapshot.getValue(ListEtudiant.class).toString());
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.d(TAG+"Removed",dataSnapshot.getValue(ListEtudiant.class).toString());
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                Log.d(TAG+"Moved",dataSnapshot.getValue(ListEtudiant.class).toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG+"Cancelled",databaseError.toString());
            }
        });
    }

    private void fetchData(DataSnapshot dataSnapshot) {
        ListEtudiant listEtudiant =dataSnapshot.getValue(ListEtudiant.class);
        mMyListEtudiants.add(listEtudiant);
        updateUI();
    }

    private void updateUI(){
        mAdapter = new ListItemsAdapter(mMyListEtudiants);
        mListItemsRecyclerView.setAdapter(mAdapter);
    }

    private class ListItemsHolder extends RecyclerView.ViewHolder{
        public TextView mNameTextView;
        public ListItemsHolder(View itemView){
            super(itemView);
            mNameTextView = (TextView) itemView.findViewById(R.id.textview_name);
        }

        public void bindData(ListEtudiant s){
            if (s.getId_Groupe().equals(IDgroupe)) {
                String test = s.getNumero_Etudiant() +
                        "\n" + s.getNom_Etudiant() +
                        "\n" + s.getPrenom_Etudiant() +
                        "\n" + s.getNiveau_Etudiant() +
                        "\n" + s.getId_Groupe();
                mNameTextView.setText(test);
            }

        }
    }
    private class ListItemsAdapter extends RecyclerView.Adapter<ListItemsHolder>{
        private ArrayList<ListEtudiant> mListEtudiants;
        public ListItemsAdapter(ArrayList<ListEtudiant> listEtudiants){
            mListEtudiants = listEtudiants;
        }
        @Override
        public ListItemsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(ListEtudiantsActivity.this);
            View view = layoutInflater.inflate(R.layout.category_list_item_1,parent,false);
            return new ListItemsHolder(view);

        }
        @Override
        public void onBindViewHolder(ListItemsHolder holder, int position) {
            ListEtudiant s = mListEtudiants.get(position);
            holder.bindData(s);
        }
        @Override
        public int getItemCount() {
            return mListEtudiants.size();
        }
    }
}