package com.ito.taxcalculation;

import com.ito.taxcalculation.api.ApiCallInterface;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by IP Vision on 9/30/2018.
 */

public class RetroClient {
    private static final String ROOT_URL = "https://jsonvat.com/";
    private static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static ApiCallInterface getApiService() {
        return getRetrofitInstance().create(ApiCallInterface.class);
    }
}
