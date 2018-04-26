package com.example.android.sampleproject;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
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
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nInfo = connMgr.getActiveNetworkInfo();

        if(nInfo == null || !nInfo.isConnected()){
            Toast.makeText(this, "No connection. Restart app with active internet.", Toast.LENGTH_LONG).show();
        }else {
            demoObservable();
        }
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
                    country.setCountryName(countries.get(i).getCountryName());
                    country.setPopulation(countries.get(i).getPopulation());
                    country.setRank(countries.get(i).getRank());
                    countryList.add(country);
                }
                countryAdapter.setCountryList(countryList);
                recyclerView.setAdapter(countryAdapter);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    @Override
    public void onClick(Country country) {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nInfo = connMgr.getActiveNetworkInfo();

        if(nInfo == null || !nInfo.isConnected()){
            Toast.makeText(this, "No connection. Try again with active internet.", Toast.LENGTH_LONG).show();
            return;
        }

        Toast.makeText(MainActivity.this, country.getCountryName(), Toast.LENGTH_SHORT).show();

        Bundle bundle = new Bundle();
        bundle.putString("FlagURL", country.getFlag());

        Intent intent = new Intent(this, FlagActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
