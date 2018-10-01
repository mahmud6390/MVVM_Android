package com.ito.taxcalculation;

import android.app.Application;
import android.content.Context;
import android.support.test.runner.AndroidJUnitRunner;

public class TestMockRunner extends AndroidJUnitRunner {

    @Override
    public Application newApplication(ClassLoader cl, String className, Context context) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        String testApplicationClassName = MyApplicationTest.class.getCanonicalName();
        return super.newApplication(cl, testApplicationClassName, context);
    }
}
