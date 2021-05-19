package com.ahmedsaadkenawy.covid19.repository;

import androidx.lifecycle.MutableLiveData;

import com.ahmedsaadkenawy.covid19.model.ApiUtilities;
import com.ahmedsaadkenawy.covid19.model.CountryData;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataRepository {

    MutableLiveData<ArrayList<CountryData>> data = new MutableLiveData<>();
    private static DataRepository instance;

    public static DataRepository getInstance() {
        if (instance == null) {
            instance = new DataRepository();
        }
        return instance;
    }

    public MutableLiveData<ArrayList<CountryData>> getData() {
        ApiUtilities.getApiInterface().getData().enqueue(new Callback<ArrayList<CountryData>>() {
            @Override
            public void onResponse(Call<ArrayList<CountryData>> call, Response<ArrayList<CountryData>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<CountryData>> call, Throwable t) {

            }
        });
        return data;
    }

}
