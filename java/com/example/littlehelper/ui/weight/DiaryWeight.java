package com.example.littlehelper.ui.weight;

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

public class DiaryWeight extends AppCompatActivity {
    private ListView listViewWeight;
    private ArrayAdapter<String> adapter;
    private List<String> diaryWeightList;
    private DatabaseReference numWeight;
    private String numStringWeight = LoginActivity.getEmail() + " Вес";

    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_weight);
        init();
        getDataFromDbWeight();
    }
    private void init(){
        listViewWeight = findViewById(R.id.view_weight);
        diaryWeightList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, diaryWeightList);
        listViewWeight.setAdapter(adapter);
        numWeight= FirebaseDatabase.getInstance().getReference(numStringWeight);
    }

    private void getDataFromDbWeight(){
        ValueEventListener vListenerWeight = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (diaryWeightList.size()>0) diaryWeightList.clear();
                for(DataSnapshot dt: dataSnapshot.getChildren()){
                    NumWeight numWeight = dt.getValue(NumWeight.class);
                    assert numWeight != null;
                    diaryWeightList.add(numWeight.datacalendarWeight + "\n" + numWeight.numStringWeight);
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        };
        numWeight.addValueEventListener(vListenerWeight);
    }

}
