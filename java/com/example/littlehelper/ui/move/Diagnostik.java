package com.example.littlehelper.ui.move;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.littlehelper.Head;
import com.example.littlehelper.Infarkt;
import com.example.littlehelper.R;


public class Diagnostik extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diagnostik);
    }
    public void onClickGoToInfarktAbd(View view) {
        Intent n = new Intent(Diagnostik.this, Infarkt.class);
        startActivity(n);
    }

    public void onClickGoToInfarkt(View view) {
        Intent n = new Intent(Diagnostik.this, Infarkt.class);
        startActivity(n);
    }

    public void onClickGoToInsult(View view) {
        Intent n = new Intent(Diagnostik.this, Head.class);
        startActivity(n);
    }
}
