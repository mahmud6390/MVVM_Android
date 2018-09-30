package com.ito.taxcalculation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by IP Vision on 9/30/2018.
 */

public class RateList {
    @SerializedName("rates")
    @Expose
    private ArrayList<Country> country = null;
    public ArrayList<Country> getCountry() {
        return country;
    }

    public void setCountry(ArrayList<Country> country) {
        this.country = country;
    }


}
