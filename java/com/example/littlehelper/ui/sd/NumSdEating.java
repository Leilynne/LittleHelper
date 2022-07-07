package com.example.littlehelper.ui.sd;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NumSdEating {
    public String numSdEating;
    public String datacalendar;
    Calendar c = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy - HH.mm");

    public NumSdEating() {
    }

    public NumSdEating(String numSdEating) {
        this.numSdEating = numSdEating;
        this.datacalendar = sdf.format(c.getTime());
    }
}
