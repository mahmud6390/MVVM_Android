package com.ito.taxcalculation.api;


import com.ito.taxcalculation.model.RateList;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by mahmud on 30-09-2018.
 */

public interface ApiCallInterface {
    @GET(".")
    Observable<RateList> getRateList();
}
