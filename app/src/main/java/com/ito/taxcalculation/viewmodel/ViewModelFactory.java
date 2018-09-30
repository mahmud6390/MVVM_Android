package com.ito.taxcalculation.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.ito.taxcalculation.repository.Repository;

import javax.inject.Inject;

/**
 * Created by mahmud on 30=09-2018.
 */

public class ViewModelFactory implements ViewModelProvider.Factory {

    private Repository repository;

    @Inject
    public ViewModelFactory(Repository repository) {
        this.repository = repository;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(CalculateTaxViewModel.class)) {
            return (T) new CalculateTaxViewModel(repository);
        }
        throw new IllegalArgumentException("Unknown class name");
    }
}
