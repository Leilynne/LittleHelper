package com.example.littlehelper.ui.ad;

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

public class DiaryAd extends AppCompatActivity {

    private ListView diaryadtext, diaryAdtextevening;
    private ArrayAdapter<String> adapter, adapterEvening;
    private List<String> diaryAdList, diaryAdEveningList;
    private DatabaseReference numAdMorning, numAdEvening;
    private String numsAdMorning = LoginActivity.getEmail() + " Утренние значения";
    private String numsAdEvening = LoginActivity.getEmail() + " Вечерние значения";


    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diaryad);
        init();
        getDataFromDbM();
        getDataFromDbE();
    }
    private void init(){
        diaryadtext = findViewById(R.id.view_ad);
        diaryAdList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, diaryAdList);
        diaryadtext.setAdapter(adapter);
        numAdMorning = FirebaseDatabase.getInstance().getReference(numsAdMorning);


        diaryAdtextevening = findViewById(R.id.view_ad_evening);
        diaryAdEveningList = new ArrayList<>();
        adapterEvening = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, diaryAdEveningList);
        diaryAdtextevening.setAdapter(adapterEvening);
        numAdEvening = FirebaseDatabase.getInstance().getReference(numsAdEvening);
    }

    private void getDataFromDbM(){
        ValueEventListener vListenerM = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (diaryAdList.size()>0) diaryAdList.clear();
                System.out.println("123456");
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    NumAd adMorning = ds.getValue(NumAd.class);
                    assert adMorning != null;
                    diaryAdList.add(adMorning.datacalendar + "\n" + adMorning.numAdSystolMorning + "/" + adMorning.numAdDiastolMorning);
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        };
        numAdMorning.addValueEventListener(vListenerM);
    }

    private void getDataFromDbE() {
        ValueEventListener vListenerE = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (diaryAdEveningList.size() > 0) diaryAdEveningList.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    NumAdEvening adEvening = ds.getValue(NumAdEvening.class);
                    assert adEvening != null;
                    diaryAdEveningList.add(adEvening.datacalendar + "\n" + adEvening.numAdSystolEvening + "/" + adEvening.numAdDiastolEvening);
                }
                adapterEvening.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        };
        numAdEvening.addValueEventListener(vListenerE);
    }
}


