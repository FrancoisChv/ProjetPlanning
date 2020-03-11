package com.example.planningproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ListGroupesActivity extends AppCompatActivity {

    private final String TAG = "ListActivity";
    DatabaseReference mDB;
    DatabaseReference mListItemRef;
    private RecyclerView mListItemsRecyclerView;
    private ListItemsAdapter mAdapter;
    private ArrayList<ListGroupes> myListItems;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_etudiant);

        mDB= FirebaseDatabase.getInstance().getReference();
        mListItemRef = mDB.child("1fvb_V3xjrGD24-k2V3TAQJznydc9qQLjSzvt-kQGodg/groupes");
        myListItems = new ArrayList<>();
        mListItemsRecyclerView = (RecyclerView)findViewById(R.id.listItem_recycler_view);
        mListItemsRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(getResources()));
        mListItemsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        updateUI();

        //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //  setSupportActionBar(toolbar);
      /*  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewListItem();
            }
        }); */

        mListItemRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.d(TAG+"Added",dataSnapshot.getValue(ListGroupes.class).toString());
                fetchData(dataSnapshot);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Log.d(TAG+"Changed",dataSnapshot.getValue(ListGroupes.class).toString());
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.d(TAG+"Removed",dataSnapshot.getValue(ListGroupes.class).toString());
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                Log.d(TAG+"Moved",dataSnapshot.getValue(ListGroupes.class).toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG+"Cancelled",databaseError.toString());
            }
        });
    }



    /* public void createNewListItem() {
        // Create new List Item  at /listItem
        final String key = FirebaseDatabase.getInstance().getReference().child("1fvb_V3xjrGD24-k2V3TAQJznydc9qQLjSzvt-kQGodg/etudiants").push().getKey();
        LayoutInflater li = LayoutInflater.from(this);
        View getListItemView = li.inflate(R.layout.dialog_get_list_item, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setView(getListItemView);
        final EditText userInput = (EditText) getListItemView.findViewById(R.id.editTextDialogUserInput);
        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // get user input and set it to result
                        // edit text
                        String listItemText = userInput.getText().toString();
                        ListItem listItem = new ListItem(listItemText);
                        Map<String, Object> listItemValues = listItem.toMap();
                        Map<String, Object> childUpdates = new HashMap<>();
                        childUpdates.put("/listEtudiant/" + key, listItemValues);
                        FirebaseDatabase.getInstance().getReference().updateChildren(childUpdates);
                    }
                }).create()
                .show();
    }*/

    private void fetchData(DataSnapshot dataSnapshot) {
        ListGroupes listItem=dataSnapshot.getValue(ListGroupes.class);
        myListItems.add(listItem);
        updateUI();
    }

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

        public void bindData(ListGroupes s){
            String temp = s.getHoraires_Debut() + "\n" + s.getHoraires_Fin() + "\n" + s.getId_Groupe() + "\n" + s.getNom_Activite();
            mNameTextView.setText(temp);
        }
    }
    private class ListItemsAdapter extends RecyclerView.Adapter<ListItemsHolder>{
        private ArrayList<ListGroupes> mListItems;
        public ListItemsAdapter(ArrayList<ListGroupes> ListItems){
            mListItems = ListItems;
        }
        @Override
        public ListItemsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(ListGroupesActivity.this);
            View view = layoutInflater.inflate(R.layout.category_list_item_1,parent,false);
            return new ListItemsHolder(view);

        }
        @Override
        public void onBindViewHolder(ListItemsHolder holder, int position) {
            ListGroupes s = mListItems.get(position);
            holder.bindData(s);
        }
        @Override
        public int getItemCount() {
            return mListItems.size();
        }
    }
}