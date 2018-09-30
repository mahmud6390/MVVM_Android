package com.ito.taxcalculation.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by IP Vision on 9/30/2018.
 */

public class Periods {
    @SerializedName("effective_from")
    private String effectiveFrom;
    @SerializedName("rates")
    private Map<String,String> rates;


    public String getEffectiveFrom() {
        return effectiveFrom;
    }

    public void setEffectiveFrom(String effectiveFrom) {
        this.effectiveFrom = effectiveFrom;
    }

    public Map<String, String> getRates() {
        return rates;
    }

    public void setRates(Map<String, String> rates) {
        this.rates = rates;
    }



    @Override
    public String toString() {
        return "Periods{" +
                "rates='" + rates.size() + '\'' +

                '}';
    }
}
