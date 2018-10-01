package com.ito.taxcalculation;

import com.ito.taxcalculation.di.AppComponent;
import com.ito.taxcalculation.view.CalculateTaxActivityTest;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Mahmud on 10/1/2018.
 */
@Singleton
@Component(modules = MockUtilModule.class)
public interface TestAppComponent extends AppComponent {
    void doInjection(CalculateTaxActivityTest activity);
}
