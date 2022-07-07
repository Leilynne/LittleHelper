package com.example.littlehelper.ui.move;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.littlehelper.NumMove;
import com.example.littlehelper.ui.login.LoginActivity;
import com.example.littlehelper.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DiaryMove extends AppCompatActivity {
    private ListView listViewMove;
    private ArrayAdapter<String> adapter;
    private List<String> diaryMoveList;
    private DatabaseReference numMove;
    private String numStringMove = LoginActivity.getEmail() + "Количество шагов: ";


    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_move);
        init();
        getDataFromDbMove();

    }
    private void init(){
        listViewMove = findViewById(R.id.view_move);
        diaryMoveList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, diaryMoveList);
        listViewMove.setAdapter(adapter);
        numMove = FirebaseDatabase.getInstance().getReference(numStringMove);
    }

    private void getDataFromDbMove(){
        ValueEventListener vListenerMove = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (diaryMoveList.size()>0) diaryMoveList.clear();
                for(DataSnapshot dt: dataSnapshot.getChildren()){
                    NumMove numMove= dt.getValue(NumMove.class);
                    assert numMove != null;
                    diaryMoveList.add(numMove.datacalendarMove + "\n" + numMove.numStringMove);
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        };
        numMove.addValueEventListener(vListenerMove);
    }
}
