package com.example.littlehelper.ui.ad;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NumAdEvening {
    public String numAdSystolEvening;
    public String numAdDiastolEvening;
    public String datacalendar;
    Calendar c = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy - HH.mm");


    public NumAdEvening() {
    }

    public NumAdEvening(String numAdSystolEvening, String numAdDiastolEvening) {
        this.numAdSystolEvening = String.valueOf(numAdSystolEvening);
        this.numAdDiastolEvening = String.valueOf(numAdDiastolEvening);
        this.datacalendar = sdf.format(c.getTime());

    }
}
