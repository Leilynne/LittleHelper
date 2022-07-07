package com.example.littlehelper.ui.weight;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NumWeight {
    public String numStringWeight;
    public String datacalendarWeight;
    Calendar c = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy - HH.mm");

    public NumWeight() {
    }

    public NumWeight(String numWeight) {
        this.numStringWeight = numWeight;
        this.datacalendarWeight = sdf.format(c.getTime());
    }
}
