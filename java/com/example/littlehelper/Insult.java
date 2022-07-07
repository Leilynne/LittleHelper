package com.example.littlehelper;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Insult extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insult);

    }
    public void onClickBackToAdAtInsult(View view) {
        Intent n = new Intent(Insult.this, MainActivity.class);
        startActivity(n);
    }

    public void onClickTiCallInsult(View view) {
        String number = "tel:112";
        Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse(number));
        startActivity(callIntent);
    }
}
