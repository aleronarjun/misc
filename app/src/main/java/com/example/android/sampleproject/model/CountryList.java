package com.example.android.sampleproject.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Arjun Vidyarthi on 26-Apr-18.
 */

public class CountryList {

    @SerializedName("worldpopulation")
    @Expose
    private List<Country> countries = null;

    public List<Country> getCountries(){
        return countries;
    }

    public void setCountries (List<Country> countries){
        this.countries = countries;
    }
}
