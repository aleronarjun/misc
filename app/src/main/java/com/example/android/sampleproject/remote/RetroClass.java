package com.example.android.sampleproject.remote;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Arjun Vidyarthi on 26-Apr-18.
 */

public class RetroClass {

    private static final String BASE_URL = "http://www.androidbegin.com/tutorial";

    private static Retrofit getRetrofitInstance(){
        return new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static APIService getAPIService(){

        return getRetrofitInstance().create(APIService.class);

    }
}
