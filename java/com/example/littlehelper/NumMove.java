package com.example.littlehelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NumMove {

        public String numStringMove;
        public String datacalendarMove;
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy - HH.mm");

        public NumMove() {
        }

        public NumMove(String numMove) {
            this.numStringMove = numMove;
            this.datacalendarMove = sdf.format(c.getTime());
        }
}
