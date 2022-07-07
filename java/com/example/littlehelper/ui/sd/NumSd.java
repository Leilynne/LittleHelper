package com.example.littlehelper.ui.sd;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NumSd {
    public String numStriinfSdHungry;
    public String datacalendarSd;
    Calendar c = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy - HH.mm");

    public NumSd() {
    }

    public NumSd(String numSdHungry) {
        this.numStriinfSdHungry = numSdHungry;
        this.datacalendarSd = sdf.format(c.getTime());
    }
}

