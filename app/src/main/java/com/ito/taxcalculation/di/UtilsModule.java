package com.ito.taxcalculation.di;

import android.arch.lifecycle.ViewModelProvider;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ito.taxcalculation.api.ApiCallInterface;
import com.ito.taxcalculation.repository.Repository;
import com.ito.taxcalculation.utils.Urls;
import com.ito.taxcalculation.viewmodel.ViewModelFactory;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mahmud on 30-09-2018.
 */

@Module
public class UtilsModule {

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder builder =
                new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return builder.setLenient().create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {

        return new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    @Provides
    @Singleton
    ApiCallInterface getApiCallInterface(Retrofit retrofit) {
        return retrofit.create(ApiCallInterface.class);
    }

    @Provides
    @Singleton
    OkHttpClient getRequestHeader() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.addInterceptor(chain -> {
            Request original = chain.request();
            Request request = original.newBuilder()
                    .build();
            return chain.proceed(request);
        })
                .connectTimeout(100, TimeUnit.SECONDS)
                .writeTimeout(100, TimeUnit.SECONDS)
                .readTimeout(300, TimeUnit.SECONDS);

        return httpClient.build();
    }

    @Provides
    @Singleton
    Repository getRepository(ApiCallInterface apiCallInterface) {
        return new Repository(apiCallInterface);
    }

    @Provides
    @Singleton
    ViewModelProvider.Factory getViewModelFactory(Repository myRepository) {
        return new ViewModelFactory(myRepository,subscribeScheduler(),observerScheduler());
    }
    @Provides
    @SubscribeScheduler
    public Scheduler subscribeScheduler() {
        return Schedulers.io();
    }

    @Provides
    @ObserverScheduler
    public Scheduler observerScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
