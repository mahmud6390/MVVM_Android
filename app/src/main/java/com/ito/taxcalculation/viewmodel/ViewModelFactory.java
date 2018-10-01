package com.ito.taxcalculation.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.ito.taxcalculation.di.ObserverScheduler;
import com.ito.taxcalculation.di.SubscribeScheduler;
import com.ito.taxcalculation.repository.Repository;

import javax.inject.Inject;

import io.reactivex.Scheduler;

/**
 * Created by mahmud on 30=09-2018.
 */

public class ViewModelFactory implements ViewModelProvider.Factory {

    private Repository repository;
    private Scheduler subscribeScheduler;
    private Scheduler observerScheduler;

    @Inject
    public ViewModelFactory(Repository repository,@SubscribeScheduler Scheduler subscribeScheduler,
                            @ObserverScheduler Scheduler observerScheduler) {
        this.repository = repository;
        this.subscribeScheduler=subscribeScheduler;
        this.observerScheduler=observerScheduler;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(CalculateTaxViewModel.class)) {
            return (T) new CalculateTaxViewModel(repository,subscribeScheduler,observerScheduler);
        }
        throw new IllegalArgumentException("Unknown class name");
    }
}
