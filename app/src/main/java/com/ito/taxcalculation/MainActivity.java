package com.ito.taxcalculation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.ito.taxcalculation.api.ApiCallInterface;
import com.ito.taxcalculation.model.Country;
import com.ito.taxcalculation.model.RateList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> countryName=new ArrayList<>();
    ArrayList<String> rateKey=new ArrayList<>();
    ArrayList<Integer> rateValue=new ArrayList<>();
    ArrayList<Country> countryArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ApiCallInterface apiService=RetroClient.getApiService();
//        Call<RateList> rateList=apiService.getRateList();
//        rateList.enqueue(new Callback<RateList>() {
//            @Override
//            public void onResponse(Call<RateList> call, Response<RateList> response) {
//                Log.d("JSON_PARSE","response>>"+response.isSuccessful());
//                if(response.isSuccessful()){
//                    countryArrayList=response.body().getCountry();
//                    for(Country country:countryArrayList){
//                        countryName.add(country.getName());
//                    }
//                    Map<String,String> rateMap=getRatesForCountry(countryArrayList.get(0).getCode());
//                    List<String> keys = new ArrayList<String>(rateMap.keySet());
//                    List<String> values = new ArrayList<String>(rateMap.values());
//                    for(String key:keys){
//                        Log.d("JSON_PARSE","key>>"+key);
//                    }
//                    for(String value:values){
//                        Log.d("JSON_PARSE","value>>"+value);
//                    }
//                    Log.d("JSON_PARSE","countryName>>"+countryName);
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<RateList> call, Throwable t) {
//                Log.d("JSON_PARSE","onFailure>>"+t.getMessage());
//            }
//        });
    }
    private Map<String,String> getRatesForCountry(String code){
        for(Country country:countryArrayList){
            if(country.getCode().equalsIgnoreCase(code)){
                return country.getPeriods().get(0).getRates();
            }
        }
        return null;
    }
}
