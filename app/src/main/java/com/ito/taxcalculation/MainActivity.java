package com.ito.taxcalculation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ApiService apiService=RetroClient.getApiService();
        Call<RateList> rateList=apiService.getRateList();
        rateList.enqueue(new Callback<RateList>() {
            @Override
            public void onResponse(Call<RateList> call, Response<RateList> response) {
                if(response.isSuccessful()){
                    Log.d("MY_RESPONSE","response:"+response.body().getCountry()) ;
                }
            }

            @Override
            public void onFailure(Call<RateList> call, Throwable t) {

            }
        });
    }
}
