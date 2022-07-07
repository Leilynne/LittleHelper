package com.example.littlehelper.ui.profile;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NameLs {
    public String nameStringLs;
    public String datacalendarLs;
    Calendar c = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy - HH.mm");

    public NameLs() {
    }

    public NameLs(String nameStringLs) {
        this.nameStringLs = nameStringLs;
        this.datacalendarLs = sdf.format(c.getTime());
    }
}