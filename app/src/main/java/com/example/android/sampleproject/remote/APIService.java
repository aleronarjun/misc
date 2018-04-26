package com.example.android.sampleproject.remote;

import com.example.android.sampleproject.model.CountryList;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Arjun Vidyarthi on 25-Apr-18.
 */

public interface APIService {

    @GET("/tutorial/jsonparsetutorial.txt")
    Observable<CountryList> getCountryList();

}
