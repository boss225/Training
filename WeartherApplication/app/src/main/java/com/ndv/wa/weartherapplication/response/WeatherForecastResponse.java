package com.ndv.wa.weartherapplication.response;

import java.util.ArrayList;

/**
 * Created by admin on 11/21/2017.
 */

public class WeatherForecastResponse {
    int cnt;
    ArrayList<Forecast> list;

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public ArrayList<Forecast> getList() {
        return list;
    }

    public void setList(ArrayList<Forecast> list) {
        this.list = list;
    }
}
