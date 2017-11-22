package com.ndv.wa.weartherapplication.response;

import java.util.ArrayList;

/**
 * Created by admin on 11/21/2017.
 */

public class Forecast {
     long dt;
     ArrayList<Weather> weather;
     Temp temp;

     public long getDt() {
          return dt;
     }

     public void setDt(long dt) {
          this.dt = dt;
     }

     public ArrayList<Weather> getWeather() {
          return weather;
     }

     public void setWeather(ArrayList<Weather> weather) {
          this.weather = weather;
     }

     public Temp getTemp() {
          return temp;
     }

     public void setTemp(Temp temp) {
          this.temp = temp;
     }
}
