package com.example.littlehelper.ui.ad;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NumAd {
    public String numAdSystolMorning;
    public String numAdDiastolMorning;
    public String datacalendar;
    Calendar c = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy - HH.mm");

    public NumAd() {
    }

    public NumAd(String numAdSystolMorning, String numAdDiastolMorning ) {
        this.numAdSystolMorning = numAdSystolMorning;
        this.numAdDiastolMorning = numAdDiastolMorning;
        this.datacalendar = sdf.format(c.getTime());
    }
}
