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
     * method to call load json response
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
    public void loadTaxTypeBasedOnCountry(String countryCode){
        Map<String,String> rateMap=getRatesForCountry(countryCode);
        ArrayList<Rates> ratesArrayList=new ArrayList<>();
        for(String key:rateMap.keySet()){
            Rates rates=new Rates();
            rates.setKey(key);
            rates.setValue(Double.parseDouble(rateMap.get(key)));
            ratesArrayList.add(rates);
        }
        rateList.setValue(ratesArrayList);
    }
    public void loadTotalAmount(double currency,double tax){
        double taxAmount=currency * (tax/100);
        double totalWithTax=currency+taxAmount;
        totalAmountWithTax.setValue(totalWithTax);
    }
    private Map<String,String> getRatesForCountry(String code){
        for(Country country:countryList.getValue()){
            if(country.getCode().equalsIgnoreCase(code)){
                return country.getPeriods().get(0).getRates();
            }
        }
        return null;
    }

    @Override
    protected void onCleared() {
        disposables.clear();
    }
}
