package com.example.littlehelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;



public class Head extends AppCompatActivity {
    private CheckBox smile, hand, speak;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.head);
        smile = findViewById(R.id.smile);
        speak = findViewById(R.id.speak);
        hand = findViewById(R.id.hand);
    }

    public void onClickToInsult(View view) {
        if(smile.isChecked() || speak.isChecked() || hand.isChecked()) {
            Intent n = new Intent(Head.this, Insult.class);
            startActivity(n);
        }
        else {
            Intent n = new Intent(Head.this, Infarkt.class);
            startActivity(n);
        }
    }
}
