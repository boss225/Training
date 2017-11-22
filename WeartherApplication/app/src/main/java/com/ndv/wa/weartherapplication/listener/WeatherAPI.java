package com.ndv.wa.weartherapplication.listener;

import com.ndv.wa.weartherapplication.response.WeatherCurrentResponse;
import com.ndv.wa.weartherapplication.response.WeatherForecastResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by admin on 11/20/2017.
 */

public interface WeatherAPI {
    String KEY_API = "95287143369c1d1d365d3109ccfe4c98";
    String CURRENT_WEATHER = "weather";
    String FORECAST_WEATHER = "forecast/daily";

    @GET(CURRENT_WEATHER)
    Call<WeatherCurrentResponse> getWeatherCurrentByCoord(@Query("lat") String lat, @Query("lon") String lon, @Query("appid") String key_api);

    @GET(CURRENT_WEATHER)
    Call<WeatherCurrentResponse> getWeatherCurrentByName(@Query("q") String city_name, @Query("appid") String key_api);

    @GET(FORECAST_WEATHER)
    Call<WeatherForecastResponse> getWeatherForecastByCoord(@Query("lat") String lat, @Query("lon") String lon, @Query("cnt") String cnt, @Query("appid") String key_api);

    @GET(FORECAST_WEATHER)
    Call<WeatherForecastResponse> getWeatherForecastByName(@Query("q") String city_name, @Query("cnt") String cnt, @Query("appid") String key_api);
}
