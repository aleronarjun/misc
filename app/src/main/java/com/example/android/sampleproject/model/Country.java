package com.example.android.sampleproject.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Arjun Vidyarthi on 26-Apr-18.
 */

public class Country {

    @SerializedName("rank")
    @Expose
    private int rank;

    @SerializedName("country")
    @Expose
    private String countryName;

    @SerializedName("population")
    @Expose
    private String population;

    @SerializedName("flag")
    @Expose
    private String flag;

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
