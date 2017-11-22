package com.ndv.wa.weartherapplication.ui.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.ndv.wa.weartherapplication.R;
import com.ndv.wa.weartherapplication.listener.SearchByLocation;
import com.ndv.wa.weartherapplication.listener.WeatherAPI;
import com.ndv.wa.weartherapplication.response.WeatherCurrentResponse;
import com.ndv.wa.weartherapplication.response.WeatherForecastResponse;
import com.ndv.wa.weartherapplication.retrofit.ApiUtils;
import com.ndv.wa.weartherapplication.ui.dialog.SearchDialog;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, SearchByLocation {
    @BindView(R.id.txt_country_name) TextView mTxtCityName;
    @BindView(R.id.txt_date_time) TextView mTxtDateTime;
    @BindView(R.id.img_wheather) ImageView mImgWeather;
    @BindView(R.id.txt_status_current) TextView mTxtStatus;
    @BindView(R.id.txt_temp_current) TextView mTxtTemp;
//    @BindView(R.id.txt_day_1) TextView mTxtDay1;
//    @BindView(R.id.txt_day_2) TextView mTxtDay2;
//    @BindView(R.id.txt_day_3) TextView mTxtDay3;
    @BindView(R.id.img_wheather_day_1) ImageView mImgWeatherDay1;
    @BindView(R.id.img_wheather_day_2) ImageView mImgWeatherDay2;
    @BindView(R.id.img_wheather_day_3) ImageView mImgWeatherDay3;
    @BindView(R.id.txt_temp_1) TextView mTxtTemp1;
    @BindView(R.id.txt_temp_2) TextView mTxtTemp2;
    @BindView(R.id.txt_temp_3) TextView mTxtTemp3;

    ImageView[] mImgList = {mImgWeatherDay1, mImgWeatherDay2, mImgWeatherDay3};
    TextView[] mTxtList = {mTxtTemp1, mTxtTemp2, mTxtTemp3};
    private Location mLocation;
    private GoogleApiClient mGoogleApiClient;
    private WeatherAPI mWeatherAPI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchDialog dialog = new SearchDialog();
                dialog.show(getFragmentManager(), "searchDialog");
            }
        });

        if (checkPlayServices()) {
            buildGoogleApiClient();
            mWeatherAPI = ApiUtils.createWeatherAPI();
        }
    }

    private void getLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 2);
        } else {
            mLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

            if (mLocation != null) {
                String latitude = String.valueOf(mLocation.getLatitude());
                String longitude = String.valueOf(mLocation.getLongitude());
                mWeatherAPI.getWeatherCurrentByCoord(latitude, longitude, WeatherAPI.KEY_API).enqueue(weatherCurrentCallBack);
                mWeatherAPI.getWeatherForecastByCoord(latitude, longitude, ApiUtils.NUM_ITEM, WeatherAPI.KEY_API).enqueue(weatherForecastCallBack);

            } else {
                Toast.makeText(this, R.string.check_gps, Toast.LENGTH_LONG).show();
            }
        }
    }

    Callback<WeatherCurrentResponse> weatherCurrentCallBack = new Callback<WeatherCurrentResponse>(){
        @Override
        public void onResponse(Call<WeatherCurrentResponse> call, Response<WeatherCurrentResponse> response) {
            if (response.isSuccessful() && response.body() != null){
                WeatherCurrentResponse data = response.body();

                mTxtCityName.setText(getResources().getString(R.string.weather_location, ApiUtils.checkEmpty(data.getName()), ApiUtils.checkEmpty(data.getSys().getCountry())));
                mTxtDateTime.setText(ApiUtils.formatDateTime(data.getDt()));
                mTxtStatus.setText(getResources().getString(R.string.weather_today, data.getWeather().get(0).getMain()));
                mTxtTemp.setText(getResources().getString(R.string.weather_temp, String.valueOf(data.getMain().getTemp_min()), String.valueOf(data.getMain().getTemp_max())));
                Glide.with(MainActivity.this).load(getResources().getString(R.string.weather_icon, data.getWeather().get(0).getIcon())).into(mImgWeather);

            }else {
                Log.d("WeatherCurrentCallBack", "Code: " + response.code() + " Message: " + response.message());
            }
        }

        @Override
        public void onFailure(Call<WeatherCurrentResponse> call, Throwable t) {
            t.printStackTrace();
        }
    };

    Callback<WeatherForecastResponse> weatherForecastCallBack = new Callback<WeatherForecastResponse>() {
        @Override
        public void onResponse(Call<WeatherForecastResponse> call, Response<WeatherForecastResponse> response) {
            if (response.isSuccessful() && response.body() != null){
                WeatherForecastResponse dataForecast = response.body();
                Glide.with(MainActivity.this).load(getResources()
                        .getString(R.string.weather_icon, dataForecast.getList().get(0).getWeather().get(0).getIcon()))
                        .into(mImgWeatherDay1);
                Glide.with(MainActivity.this).load(getResources()
                        .getString(R.string.weather_icon, dataForecast.getList().get(1).getWeather().get(0).getIcon()))
                        .into(mImgWeatherDay2);
                Glide.with(MainActivity.this).load(getResources()
                        .getString(R.string.weather_icon, dataForecast.getList().get(2).getWeather().get(0).getIcon()))
                        .into(mImgWeatherDay3);

                mTxtTemp1.setText(getResources().getString(R.string.weather_temp,
                        String.valueOf(dataForecast.getList().get(0).getTemp().getMin()),
                        String.valueOf(dataForecast.getList().get(0).getTemp().getMax())));
                mTxtTemp2.setText(getResources().getString(R.string.weather_temp,
                        String.valueOf(dataForecast.getList().get(0).getTemp().getMin()),
                        String.valueOf(dataForecast.getList().get(0).getTemp().getMax())));
                mTxtTemp3.setText(getResources().getString(R.string.weather_temp,
                        String.valueOf(dataForecast.getList().get(0).getTemp().getMin()),
                        String.valueOf(dataForecast.getList().get(0).getTemp().getMax())));


            }else {
                Log.d("WeatherCurrentCallBack", "Code: " + response.code() + " Message: " + response.message());
            }
        }

        @Override
        public void onFailure(Call<WeatherForecastResponse> call, Throwable t) {
            t.printStackTrace();
        }
    };

    protected synchronized void buildGoogleApiClient() {
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API).build();
        }
    }

    private boolean checkPlayServices() {
        return GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this)
                == ConnectionResult.SUCCESS;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_settings:
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        getLocation();
    }

    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();
    }

//    @Override
//    protected void onStart() {
//        mGoogleApiClient.connect();
//        mWeatherAPI = ApiUtils.createWeatherAPI();
//        super.onStart();
//    }

    @Override
    protected void onResume() {
        super.onResume();
        mGoogleApiClient.connect();
        mWeatherAPI = ApiUtils.createWeatherAPI();
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this, "Connection errors: " + connectionResult.getErrorMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getWeatherByLocation(String lat, String lon) {
        mWeatherAPI.getWeatherCurrentByCoord(lat, lon, WeatherAPI.KEY_API).enqueue(weatherCurrentCallBack);
        mWeatherAPI.getWeatherForecastByCoord(lat, lon, ApiUtils.NUM_ITEM, WeatherAPI.KEY_API).enqueue(weatherForecastCallBack);
    }
}
