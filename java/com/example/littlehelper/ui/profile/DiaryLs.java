package com.example.littlehelper.ui.profile;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.littlehelper.R;
import com.example.littlehelper.ui.login.LoginActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DiaryLs extends AppCompatActivity {
    private ListView listViewLs;
    private ArrayAdapter<String> adapter;
    private List<String> diaryLsList;
    private DatabaseReference nameDBLs;
    private String nameStringLs = LoginActivity.getEmail() + " Название препарата";


    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_ls);
        init();
        getDataFromDBls();
    }
    private void init(){
        listViewLs = findViewById(R.id.diaryLs);
        diaryLsList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, diaryLsList);
        listViewLs.setAdapter(adapter);
        nameDBLs = FirebaseDatabase.getInstance().getReference(nameStringLs);

    }

    private void getDataFromDBls(){
        ValueEventListener vListenerLs = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (diaryLsList.size()>0) diaryLsList.clear();
                for(DataSnapshot dt: dataSnapshot.getChildren()){
                    NameLs nameLs = dt.getValue(NameLs.class);
                    assert nameLs != null;
                    diaryLsList.add(nameLs.datacalendarLs + "\n" + nameLs.nameStringLs);
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        };
        nameDBLs.addValueEventListener(vListenerLs);
    }

}
