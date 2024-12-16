package com.metbetestimate.betestimate.models;

import java.sql.Timestamp;

public class DateRange {
    public Timestamp getDate1() {
        return date1;
    }
    public void setDate1(Timestamp date1) {
        this.date1 = date1;
    }
    private Timestamp date1;
    private Timestamp date2;
    public Timestamp getDate2() {
        return date2;
    }
    public void setDate2(Timestamp date2) {
        this.date2 = date2;
    }
}
