package com.ito.taxcalculation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by IP Vision on 9/30/2018.
 */

public class Periods {
    @SerializedName("effective_from")
    @Expose
    private String effectiveFrom;

    public String getEffectiveFrom() {
        return effectiveFrom;
    }

    public void setEffectiveFrom(String effectiveFrom) {
        this.effectiveFrom = effectiveFrom;
    }

    public ArrayList<Map<String, String>> getRates() {
        return rates;
    }

    public void setRates(ArrayList<Map<String, String>> rates) {
        this.rates = rates;
    }

    @SerializedName("rates")
    @Expose
    private ArrayList<Map<String,String>> rates;

    @Override
    public String toString() {
        return "Periods{" +
                "effectiveFrom='" + effectiveFrom + '\'' +
                ", rates=" + rates +
                '}';
    }
}
