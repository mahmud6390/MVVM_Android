package com.ito.taxcalculation;

import android.support.annotation.Keep;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by IP Vision on 9/30/2018.
 */

public interface ApiService {
    @GET(".")
    Call<RateList> getRateList();
}
