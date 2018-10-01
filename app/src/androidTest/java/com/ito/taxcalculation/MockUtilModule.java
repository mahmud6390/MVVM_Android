package com.ito.taxcalculation;

import com.ito.taxcalculation.api.ApiCallInterface;
import com.ito.taxcalculation.di.ObserverScheduler;
import com.ito.taxcalculation.di.SubscribeScheduler;

import org.mockito.Mockito;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import retrofit2.Retrofit;

/**
 * Created by Mahmud on 10/1/2018.
 */
@Module
public class MockUtilModule {
    @Provides
    @SubscribeScheduler
    public Scheduler subscribeScheduler() {
        return Mockito.mock(Scheduler.class);
    }

    @Singleton
    @Provides
    @ObserverScheduler
    public Scheduler observerScheduler() {
        return Mockito.mock(Scheduler.class);
    }
    @Provides
    @Singleton
    ApiCallInterface getApiCallInterface(Retrofit retrofit) {
        return Mockito.mock(ApiCallInterface.class);
    }
}
