package com.ito.taxcalculation;

import android.support.test.InstrumentationRegistry;

import com.ito.taxcalculation.di.AppModule;
import com.ito.taxcalculation.di.DaggerAppComponent;
import com.ito.taxcalculation.di.UtilsModule;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Mahmud on 10/1/2018.
 */
public class MyApplicationTest {
    TestAppComponent testAppComponent;
    @Test
    public void onCreate() throws Exception {
        testAppComponent =  DaggerTestAppComponent.builder().build();
    }

    @Test
    public void getAppComponent() throws Exception {
    }

}