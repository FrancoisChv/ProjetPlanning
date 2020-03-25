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

public class ListActivitesActivity extends AppCompatActivity {

    private final String TAG = "ListActivity";
    DatabaseReference mDB;
    DatabaseReference mListItemRef;
    private RecyclerView mListItemsRecyclerView;
    private ListItemsAdapter mAdapter;
    private ArrayList<ListActivite> mMyListActivites;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_list_activite);

        mDB= FirebaseDatabase.getInstance().getReference();
        mListItemRef = mDB.child("1fvb_V3xjrGD24-k2V3TAQJznydc9qQLjSzvt-kQGodg/activites");
        mMyListActivites = new ArrayList<>();
        mListItemsRecyclerView = (RecyclerView)findViewById(R.id.listItem_recycler_view);
        mListItemsRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(getResources()));
        mListItemsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        updateUI();

        /* Permet l'affichage A GARDER */
        mListItemRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.d(TAG+"Added",dataSnapshot.getValue(ListActivite.class).toString());
                fetchData(dataSnapshot);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Log.d(TAG+"Changed",dataSnapshot.getValue(ListActivite.class).toString());
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.d(TAG+"Removed",dataSnapshot.getValue(ListActivite.class).toString());
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                Log.d(TAG+"Moved",dataSnapshot.getValue(ListActivite.class).toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG+"Cancelled",databaseError.toString());
            }
        });
    }

    private void fetchData(DataSnapshot dataSnapshot) {
        ListActivite listActivite =dataSnapshot.getValue(ListActivite.class);
        mMyListActivites.add(listActivite);
        updateUI();
    }

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
            LayoutInflater layoutInflater = LayoutInflater.from(ListActivitesActivity.this);
            View view = layoutInflater.inflate(R.layout.category_list_item_1,parent,false);
            return new ListItemsHolder(view);

        }
        @Override
        public void onBindViewHolder(ListItemsHolder holder, int position) {
            ListActivite s = mListActivites.get(position);
            holder.bindData(s);
        }
        @Override
        public int getItemCount() {
            return mMyListActivites.size();
        }
    }
}