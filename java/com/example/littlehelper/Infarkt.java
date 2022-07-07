package com.example.littlehelper;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Infarkt extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diagnostik_infarkt);
    }

    public void onClickBackToAdAtIm(View view) {
        Intent n = new Intent(Infarkt.this, MainActivity.class);
        startActivity(n);
    }

    public void onClickCall(View view) {
        String number = "tel:+112";
        Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse(number));
        startActivity(callIntent);
    }
}
