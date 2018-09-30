package com.ito.taxcalculation;

import android.app.Application;
import android.content.Context;

import com.ito.taxcalculation.di.AppComponent;
import com.ito.taxcalculation.di.AppModule;
import com.ito.taxcalculation.di.DaggerAppComponent;
import com.ito.taxcalculation.di.UtilsModule;


/**
 * Created by mahmud on 30-09-2018.
 */

public class MyApplication extends Application {
    AppComponent appComponent;
    Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).utilsModule(new UtilsModule()).build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }
}
