package com.ito.taxcalculation.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;

import com.ito.taxcalculation.model.Country;
import com.ito.taxcalculation.model.RateList;
import com.ito.taxcalculation.repository.Repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import io.reactivex.schedulers.TestScheduler;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by IP Vision on 10/1/2018.
 */
public class CalculateTaxViewModelTest {

    CalculateTaxViewModel viewModel;
    @Mock
    Repository repository;
    TestScheduler testScheduler = new TestScheduler();
    @Mock
    Observer<ArrayList<Country>> countryListObserve;
    private MutableLiveData<ArrayList<Country>> countryList;
    @Captor
    ArgumentCaptor<RateList> rateListArgumentCaptor=ArgumentCaptor.forClass(RateList.class);

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        viewModel=new CalculateTaxViewModel(repository,testScheduler,testScheduler);

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getCountryList() throws Exception {
        viewModel.loadJson();
        testScheduler.triggerActions();
        viewModel.getCountryList().observeForever(countryListObserve);
        verify(countryListObserve, times(2)).onChanged(rateListArgumentCaptor.capture().getCountry());
        RateList rateList=rateListArgumentCaptor.getAllValues().get(0);
        ArrayList<Country> country=rateList.getCountry();
        assertThat(country.get(0).getName(),is(equalTo("Spain")));
    }

    @Test
    public void getRateList() throws Exception {
    }

    @Test
    public void getTotalAmountWithTax() throws Exception {
    }

    @Test
    public void loadJson() throws Exception {
    }

    @Test
    public void loadTaxTypeBasedOnCountry() throws Exception {
    }

    @Test
    public void loadTotalAmount() throws Exception {
    }

}