package com.ndv.wa.weartherapplication.ui.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.ndv.wa.weartherapplication.R;
import com.ndv.wa.weartherapplication.listener.SearchByLocation;
import com.ndv.wa.weartherapplication.listener.WeatherAPI;
import com.ndv.wa.weartherapplication.response.WeatherCurrentResponse;
import com.ndv.wa.weartherapplication.retrofit.ApiUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by admin on 11/19/2017.
 */

public class SearchDialog extends DialogFragment {
    EditText mEdtSearch;
    private SearchByLocation mSearchByLocation;
    private WeatherAPI mWeatherAPI;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mSearchByLocation = (SearchByLocation) getActivity();

        MaterialDialog.Builder dialog = new MaterialDialog.Builder(getActivity())
                .title(R.string.title_dialog_search)
                .customView(R.layout.dialog_search, true)
                .positiveText(R.string.btn_submit)
                .negativeText(R.string.btn_cancel)
                .autoDismiss(false)
                .canceledOnTouchOutside(true)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        mEdtSearch = (EditText) dialog.findViewById(R.id.edt_search_city);
                        mWeatherAPI.getWeatherCurrentByName(mEdtSearch.getText().toString().trim(), WeatherAPI.KEY_API).enqueue(weatherCurrentByName);
//                        Toast.makeText(getActivity(), mEdtSearch.getText().toString().trim(), Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                });
        return dialog.build();
    }

    Callback<WeatherCurrentResponse> weatherCurrentByName = new Callback<WeatherCurrentResponse>() {
        @Override
        public void onResponse(Call<WeatherCurrentResponse> call, Response<WeatherCurrentResponse> response) {
            if (response.isSuccessful() && response.body() != null){
                WeatherCurrentResponse data = response.body();
                String lat = String.valueOf(data.getCoord().getLat());
                String lon = String.valueOf(data.getCoord().getLon());

                mSearchByLocation.getWeatherByLocation(lat, lon);
            }else {
                Log.d("WeatherCurrentByName", "Code: " + response.code() + " Message: " + response.message());
            }
        }

        @Override
        public void onFailure(Call<WeatherCurrentResponse> call, Throwable t) {
            t.printStackTrace();
        }
    };
}
