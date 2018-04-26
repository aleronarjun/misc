package com.example.android.sampleproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.android.sampleproject.model.Country;
import com.example.android.sampleproject.model.CountryList;
import com.example.android.sampleproject.remote.APIService;
import com.example.android.sampleproject.remote.RetroClass;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.*;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity implements CountryAdapter.CountryAdapterOnClickHandler {

    private RecyclerView recyclerView;
    private List<Country> countryList;
    private CountryAdapter countryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView =  findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(false);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        countryAdapter = new CountryAdapter(this, this);

        demoObservable();

    }

    private void demoObservable() {

        APIService apiService = RetroClass.getAPIService();

        Observable<CountryList> observable = apiService.getCountryList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(new Observer<CountryList>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(CountryList value) {
                countryList = new ArrayList<>();
                List<Country> countries = value.getCountries();

                for(int i = 0; i<countries.size(); i++){

                    Country country = new Country();
                    country.setFlag(countries.get(i).getFlag());
                    countryList.add(country);
                }
                countryAdapter.setCountryList(countryList);
                recyclerView.setAdapter(countryAdapter);
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(MainActivity.this, "onError", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onComplete() {

            }
        });

    }

    @Override
    public void onClick(Country country) {

    }
}
