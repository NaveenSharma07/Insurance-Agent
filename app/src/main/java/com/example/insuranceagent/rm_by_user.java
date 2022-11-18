package com.example.insuranceagent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.insuranceagent.adapters.adapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class rm_by_user extends AppCompatActivity {
    RecyclerView recyclerview;
    DatabaseReference reference;
    adapter Adapter;
    ArrayList<userdata> list;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rm_by_user);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        Calendar calendar = Calendar.getInstance();
//        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());

        recyclerview = findViewById(R.id.userList);
        reference = FirebaseDatabase.getInstance().getReference().child("Assign Policy");
        list = new ArrayList<>();
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        Adapter = new adapter(this,list);
        recyclerview.setAdapter(Adapter);
//        initSearchWidgets();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                    for (int i=0;  i<= list.size(); i++) {
//                        String dateofexpiry=String.valueOf(i);
//                        userdata dateofexpiryy = dataSnapshot.child("dateofexpiry").getValue(userdata.class);
//                        List.add(dateofexpiryy);
//                        if(dateofexpiryy.compareTo(currentDate) > 0) {
////                            System.out.println("Date 1 occurs after Date 2");
                            userdata name = dataSnapshot.getValue(userdata.class);
                            list.add(name);
//                        } else if(dateofexpiry.compareTo(currentDate) < 0) {
////                            System.out.println("Date 1 occurs before Date 2");
//                        } else if(dateofexpiry.compareTo(currentDate) == 0) {
////                            System.out.println("Both dates are equal");
//                        }
//                    userdata dateofexpiryy = dataSnapshot.child("dateofexpiry").getValue(userdata.class);
//                    list.add(dateofexpiryy);
                    }
                Adapter.notifyDataSetChanged();
                Toast.makeText(rm_by_user.this, "data fetched", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
//    private void initSearchWidgets(){
//        SearchView searchView = (SearchView) findViewById(R.id.search_view);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String s) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String s) {
//                ArrayList<SearchView> filtereddata = new ArrayList<SearchView>();
//                for(SearchView searchView : list){
//                    if(searchView.getUser().toLowerCase().contains(s.toLowerCase())){
//
//                    }
//                }
//                return false;
//            }
//        });
//    }
//    public void fetchdata(){
//        Reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot categoryData : snapshot.getChildren()) {
//                    String dateofissue = categoryData.child("date of expiry").getValue(String.class);
//                    List.add(dateofissue);
//                }
//                adapter.notifyDataSetChanged();
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.mainmenu,menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                rm_by_user.this.Adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Adapter.getFilter().filter(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}