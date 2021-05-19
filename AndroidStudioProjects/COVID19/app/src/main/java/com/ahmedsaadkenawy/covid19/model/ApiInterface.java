package com.ahmedsaadkenawy.covid19.model;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    public static final String BASE_URL="https://corona.lmao.ninja/v2/";
    @GET("countries")
    Call<ArrayList<CountryData>> getData();
}
