package com.ndv.wa.weartherapplication.retrofit;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ndv.wa.weartherapplication.listener.WeatherAPI;
import com.ndv.wa.weartherapplication.response.Weather;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by admin on 11/21/2017.
 */

public class ApiUtils {
    public final static String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    public final static String FORMAT_DATE_TIME = "yyyy-MM-dd HH:mm a";
    public final static double K_TO_C = 273.15;
    public final static String NUM_ITEM = "3";

    public static WeatherAPI createWeatherAPI() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
        String BASE_URL = ApiUtils.BASE_URL;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(WeatherAPI.class);
    }

    public static String formatDateTime(long l) {
        Date date = new Date(l * 1000L); // *1000 is to convert seconds to milliseconds
        SimpleDateFormat sdf = new SimpleDateFormat(ApiUtils.FORMAT_DATE_TIME); // the format of your date
        String formattedDate = sdf.format(date);
        return formattedDate;
    }

    public static String checkEmpty(String s){
        return !TextUtils.isEmpty(s) ? s : "";
    }
}
