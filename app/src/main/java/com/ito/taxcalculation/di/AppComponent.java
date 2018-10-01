package com.ito.taxcalculation.di;


import android.app.Activity;

import com.ito.taxcalculation.view.CalculateTaxActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by mahmud on 30-09-2018.
 */

@Component(modules = {AppModule.class, UtilsModule.class})
@Singleton
public interface AppComponent {

    void doInjection(CalculateTaxActivity activity);

}
