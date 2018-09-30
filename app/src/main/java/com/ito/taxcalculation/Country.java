package com.ito.taxcalculation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IP Vision on 9/30/2018.
 */

public class Country {
    @SerializedName("name")
    @Expose
    private String  name;

    @SerializedName("code")
    @Expose
    private String  code;
    @SerializedName("country_code")
    @Expose
    private String  country_code;

    public ArrayList<Periods> getPeriods() {
        return periods;
    }

    public void setPeriods(ArrayList<Periods> periods) {
        this.periods = periods;
    }

    @SerializedName("periods")
    @Expose
    private ArrayList<Periods> periods;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", country_code='" + country_code + '\'' +
                ", periods=" + periods.size() +
                '}';
    }
}
