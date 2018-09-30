package com.ito.taxcalculation.repository;

import com.google.gson.JsonElement;
import com.ito.taxcalculation.api.ApiCallInterface;
import com.ito.taxcalculation.model.RateList;

import io.reactivex.Observable;

/**
 * Created by Mahmud on 30-09-2018.
 */

public class Repository {

    private ApiCallInterface apiCallInterface;

    public Repository(ApiCallInterface apiCallInterface) {
        this.apiCallInterface = apiCallInterface;
    }

    /*
     * method to call login api
     * */
    public Observable<RateList> executeApiCall() {
        return apiCallInterface.getRateList();
    }

}
