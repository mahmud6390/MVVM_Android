package com.ito.taxcalculation.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.ito.taxcalculation.di.ObserverScheduler;
import com.ito.taxcalculation.di.SubscribeScheduler;
import com.ito.taxcalculation.model.Country;
import com.ito.taxcalculation.model.RateList;
import com.ito.taxcalculation.model.Rates;
import com.ito.taxcalculation.repository.Repository;

import java.util.ArrayList;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by mahmud on 30-09-2018.
 */

public class CalculateTaxViewModel extends ViewModel {

    private Repository repository;
    private final CompositeDisposable disposables = new CompositeDisposable();
   // private final MutableLiveData<RateList> responseLiveData = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Country>> countryList = new MutableLiveData<>();
    private MutableLiveData<Double> totalAmountWithTax = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Rates>> rateList = new MutableLiveData<>();
    private Scheduler subscribeScheduler;
    private Scheduler observerScheduler;

    /**
     * This constructor create whenever viewModelFactory inject.subscribeScheduler and observerScheduler
     * subscribe on io and observeOn on Android main thread.By this qualifier it'll pass the value
     * from test case also.
     * @param repository
     * @param subscribeScheduler
     * @param observerScheduler
     */

    CalculateTaxViewModel(Repository repository,@SubscribeScheduler Scheduler subscribeScheduler,
                          @ObserverScheduler Scheduler observerScheduler) {
        this.repository = repository;
        this.subscribeScheduler=subscribeScheduler;
        this.observerScheduler=observerScheduler;
    }
    public MutableLiveData<ArrayList<Country>> getCountryList() {
        return countryList;
    }
    public MutableLiveData<ArrayList<Rates>> getRateList() {
        return rateList;
    }
    public MutableLiveData<Double> getTotalAmountWithTax() {
        return totalAmountWithTax;
    }


    /*
     * parsing json data and send the country list to observers.In this country list it's also exist the
     * tax type and value as key,value map.
     * */
    public void loadJson() {
        disposables.add(repository.executeApiCall()
                .subscribeOn(subscribeScheduler)
                .observeOn(observerScheduler)
                .subscribe(result -> {
                    //responseLiveData.setValue(result);
                    countryList.setValue(result.getCountry());
                })
        );

    }

    /**
     * By country code first it's find out the tax key,value map from getRatesForCountry.
     * After getting the result it's store the key,value as a list of Rate object.Then store at
     * live data rateLite.
     * @param countryCode
     */
    public void loadTaxTypeBasedOnCountry(String countryCode){
        Map<String,String> rateMap=getRatesForCountry(countryCode);
        if(rateMap!=null){
            ArrayList<Rates> ratesArrayList=new ArrayList<>();
            for(String key:rateMap.keySet()){
                Rates rates=new Rates();
                rates.setKey(key);
                rates.setValue(Double.parseDouble(rateMap.get(key)));
                ratesArrayList.add(rates);
            }
            rateList.setValue(ratesArrayList);
        }

    }

    /**
     * getting the currency amount and tax value it'll return the total amount with tax.And store the
     * value at LiveData totalAmountWithTax.
     * @param currency
     * @param tax
     */
    public void loadTotalAmount(double currency,double tax){
        double taxAmount=currency * (tax/100);
        double totalWithTax=currency+taxAmount;
        totalAmountWithTax.setValue(totalWithTax);
    }

    /**
     * This method return  the zero index rate list(tax key,value) for a specific country.
     * There has multiple list of rates but here we are using only zero index value.
     * Later we can change this based on effective_from date.
     * @param code
     * @return
     */
    private Map<String,String> getRatesForCountry(String code){
        if(countryList!=null && countryList.getValue()!=null){
            for(Country country:countryList.getValue()){
                if(country.getCode().equalsIgnoreCase(code)){
                    return country.getPeriods().get(0).getRates();
                }
            }
        }
        return null;
    }

    @Override
    protected void onCleared() {
        disposables.clear();
    }
}
