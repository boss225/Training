package com.ndv.wa.weartherapplication.response;

import com.ndv.wa.weartherapplication.retrofit.ApiUtils;

/**
 * Created by admin on 11/21/2017.
 */

public class Main {
    double temp;
    double pressure;
    double humidity;
    double temp_min;
    double temp_max;

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getTemp_min() {
        return temp_min - ApiUtils.K_TO_C;
    }

    public void setTemp_min(double temp_min) {
        this.temp_min = temp_min;
    }

    public double getTemp_max() {
        return temp_max - ApiUtils.K_TO_C;
    }

    public void setTemp_max(double temp_max) {
        this.temp_max = temp_max;
    }
}
