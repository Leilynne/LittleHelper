package com.example.littlehelper.ui.sd;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.littlehelper.ui.login.LoginActivity;
import com.example.littlehelper.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DiarySd extends AppCompatActivity {
    private ListView listViewSdHungry, listViewSdEating;
    private ArrayAdapter<String> adapter, adapterEvening;
    private List<String> diarySdList, diarySdEatingList;
    private DatabaseReference numSdHungry, numSdEating;
    private String numsSdHungry = LoginActivity.getEmail() + " До еды";
    private String numsSdEating = LoginActivity.getEmail() + " После еды";

    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_sd);
        init();
        getDataFromDbSdHungry();
        getDataFromDbSdEating();
    }
    private void init(){
        listViewSdHungry = findViewById(R.id.listViewSdHungry);
        diarySdList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, diarySdList);
        listViewSdHungry.setAdapter(adapter);
        numSdHungry = FirebaseDatabase.getInstance().getReference(numsSdHungry);

        listViewSdEating = findViewById(R.id.listViewSdEating);
        diarySdEatingList = new ArrayList<>();
        adapterEvening = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, diarySdEatingList);
        listViewSdEating.setAdapter(adapterEvening);
        numSdEating = FirebaseDatabase.getInstance().getReference(numsSdEating);
    }

    private void getDataFromDbSdHungry(){
        ValueEventListener vListenerSdHungry = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (diarySdList.size()>0) diarySdList.clear();
                for(DataSnapshot dt: dataSnapshot.getChildren()){
                    NumSd numSdHungry = dt.getValue(NumSd.class);
                    assert numSdHungry != null;
                    diarySdList.add(numSdHungry.datacalendarSd + "\n" + numSdHungry.numStriinfSdHungry);
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        };
        numSdHungry.addValueEventListener(vListenerSdHungry);
    }

    private void getDataFromDbSdEating() {
        ValueEventListener vListenerSdEating = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (diarySdEatingList.size() > 0) diarySdEatingList.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    NumSdEating sdEating = ds.getValue(NumSdEating.class);
                    assert sdEating != null;
                    diarySdEatingList.add(sdEating.datacalendar + "\n" + sdEating.numSdEating);
                }
                adapterEvening.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        };
        numSdEating.addValueEventListener(vListenerSdEating);
    }

}
