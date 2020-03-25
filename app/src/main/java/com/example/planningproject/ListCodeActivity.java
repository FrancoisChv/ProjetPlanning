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

public class ListCodeActivity extends AppCompatActivity {

    private final String TAG = "ListActivity";
    DatabaseReference mDB;
    DatabaseReference mListItemRef;
    private RecyclerView mListItemsRecyclerView;
    private ListItemsAdapter mAdapter;
    private ArrayList<ListCode> mMyListCodes;
    private String str1;
    private TextView test;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_list_items);
        test = findViewById(R.id.test);


        Intent I = getIntent();
        if (I.hasExtra("code")) {
            str1 = I.getStringExtra("code");
        }

        mDB= FirebaseDatabase.getInstance().getReference();
        mListItemRef = mDB.child("1fvb_V3xjrGD24-k2V3TAQJznydc9qQLjSzvt-kQGodg/codes");
        mMyListCodes = new ArrayList<>();
        mListItemsRecyclerView = (RecyclerView)findViewById(R.id.listItem_recycler_view);
        mListItemsRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(getResources()));
        mListItemsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        updateUI();

        mListItemRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.d(TAG+"Added",dataSnapshot.getValue(ListCode.class).toString());
                fetchData(dataSnapshot);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Log.d(TAG+"Changed",dataSnapshot.getValue(ListCode.class).toString());
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.d(TAG+"Removed",dataSnapshot.getValue(ListCode.class).toString());
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                Log.d(TAG+"Moved",dataSnapshot.getValue(ListCode.class).toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG+"Cancelled",databaseError.toString());
            }
        });
    }

    private void fetchData(DataSnapshot dataSnapshot) {
        ListCode listCode =dataSnapshot.getValue(ListCode.class);
        mMyListCodes.add(listCode);
        updateUI();
    }

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

        public void bindData(ListCode s){
            String result = "";
            if (s.getCode_Acces().equals(str1)) {
                String id_groupe = s.getId_Groupe();
                Intent I = new Intent(ListCodeActivity.this, UserActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("idGroupe", id_groupe);
                I.putExtras(bundle);
                startActivity(I);
            } else if (str1.equals("ADMIN")) {
                result = s.getId_Groupe() + "\n" + s.getCode_Acces();
                Intent I = new Intent(ListCodeActivity.this, UserActivity.class);
                startActivity(I);
                mNameTextView.setText(result);
            } else {
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
        public void onBindViewHolder(ListItemsHolder holder, int position) {
            ListCode s = mListCodes.get(position);
            holder.bindData(s);
        }

        @Override
        public int getItemCount() {
            return mMyListCodes.size();
        }
    }

}