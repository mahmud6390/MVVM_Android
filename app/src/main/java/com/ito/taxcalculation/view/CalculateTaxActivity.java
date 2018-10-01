package com.ito.taxcalculation.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.ito.taxcalculation.MyApplication;
import com.ito.taxcalculation.R;
import com.ito.taxcalculation.model.Country;
import com.ito.taxcalculation.model.Rates;
import com.ito.taxcalculation.viewmodel.CalculateTaxViewModel;
import com.ito.taxcalculation.viewmodel.ViewModelFactory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mahmud on 10/1/2018.
 */

public class CalculateTaxActivity extends AppCompatActivity {
    private final String TAG="CalculateTaxScreen";

    @BindView(R.id.edit_text_amount)
    EditText editTextAmount;
    @BindView(R.id.spinner_country)
    Spinner spinnerCountry;
    @BindView(R.id.radio_group_tax)
    RadioGroup radioGroup;
    @BindView(R.id.text_view_tax_value)
    TextView textViewTaxValue;
    @BindView(R.id.text_view_total_tax)
    TextView textViewTotalTax;

    @Inject
    ViewModelFactory viewModelFactory;

    private CalculateTaxViewModel viewModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_tax);
        ButterKnife.bind(this);
        ((MyApplication) getApplication()).getAppComponent().doInjection(this);
        viewModel= ViewModelProviders.of(this,viewModelFactory).get(CalculateTaxViewModel.class);
        viewModel.getCountryList().observe(this,this::updateCountry);
        viewModel.loadJson();

    }
    private void loadTaxTypeBasedOnCountryCode(String code){
        viewModel.getRateList().observe(this,this::updateTaxRadioBtn);
        viewModel.loadTaxTypeBasedOnCountry(code);


    }
    private void calculateTax(double value){
        if(!TextUtils.isEmpty(editTextAmount.getText().toString())){
            viewModel.getTotalAmountWithTax().observe(this,this::updateTaxCalculation);
            double amount=Double.parseDouble(editTextAmount.getText().toString());
            viewModel.loadTotalAmount(amount,value);
        }

    }

    private void updateCountry(ArrayList<Country> countries) {
        //one fake country by default for showing select country text
        Country country=new Country();
        country.setName(getString(R.string.select_country));
        country.setCode("");
        countries.add(0,country);
        ArrayList<String> countryNameList=new ArrayList<>();
        for(Country country1:countries){
            countryNameList.add(country1.getName());
        }
        ArrayAdapter<String> countryListAdapter=new ArrayAdapter<String>(this,R.layout.spinner_item,R.id.text_view_spinner_item,countryNameList);

        spinnerCountry.setAdapter(countryListAdapter);
        spinnerCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String code=countries.get(position).getCode();
                if(!TextUtils.isEmpty(code)){
                    loadTaxTypeBasedOnCountryCode(code);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void updateTaxRadioBtn(ArrayList<Rates> rates) {
        radioGroup.removeAllViews();
        int noOfButton=rates.size();
        for (int i=0;i<noOfButton;i++){
            RadioButton button = new RadioButton(this);
            button.setId(i);
            button.setText(rates.get(i).getKey());
            radioGroup.addView(button);
            if(button.getId()==0){
                button.setChecked(true);
                int position=button.getId();
                onTextTypeChange(rates.get(position).getValue());
            }
            button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        int position=button.getId();
                        onTextTypeChange(rates.get(position).getValue());

                    }

                }
            });
        }


    }
    private void onTextTypeChange(double value){
        textViewTaxValue.setText(String.format(getString(R.string.tax_value),value));
        calculateTax(value);
    }
    private void updateTaxCalculation(double value){
        textViewTotalTax.setText(String.format(getString(R.string.total_amount),value));
    }
}
