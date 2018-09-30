package com.ito.taxcalculation.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.ito.taxcalculation.model.Country;
import com.ito.taxcalculation.model.RateList;
import com.ito.taxcalculation.model.Rates;
import com.ito.taxcalculation.repository.Repository;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by mahmud on 30-09-2018.
 */

public class CalculateTaxViewModel extends ViewModel {

    private Repository repository;
    private final CompositeDisposable disposables = new CompositeDisposable();
    private final MutableLiveData<RateList> responseLiveData = new MutableLiveData<>();

    public MutableLiveData<ArrayList<Country>> getCountryList() {
        return countryList;
    }

    private MutableLiveData<ArrayList<Country>> countryList = new MutableLiveData<>();
    private ArrayList<Rates> ratesArrayList = new ArrayList<>();
    private RateList rateList;

    public CalculateTaxViewModel(Repository repository) {
        this.repository = repository;
    }

    public MutableLiveData<RateList> getResponseLiveData() {
        return responseLiveData;
    }

    /*
     * method to call load json response
     * */
    public void loadJson() {
        Observable<RateList> rateListObservable = repository.executeApiCall();
        disposables.add(rateListObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    responseLiveData.setValue(result);
                    countryList.setValue(result.getCountry());
                })
        );

    }

    @Override
    protected void onCleared() {
        disposables.clear();
    }
}
