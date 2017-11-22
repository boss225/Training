package com.ndv.wa.weartherapplication.response;

import com.ndv.wa.weartherapplication.retrofit.ApiUtils;

/**
 * Created by admin on 11/21/2017.
 */

public class Temp {
    double day;
    double min;
    double max;
    double night;
    double eve;
    double morn;

    public double getDay() {
        return day;
    }

    public void setDay(double day) {
        this.day = day;
    }

    public double getMin() {
        return min - ApiUtils.K_TO_C;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max - ApiUtils.K_TO_C;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getNight() {
        return night;
    }

    public void setNight(double night) {
        this.night = night;
    }

    public double getEve() {
        return eve;
    }

    public void setEve(double eve) {
        this.eve = eve;
    }

    public double getMorn() {
        return morn;
    }

    public void setMorn(double morn) {
        this.morn = morn;
    }
}
