package com.ahmedsaadkenawy.covid19.view;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ahmedsaadkenawy.covid19.model.CountryData;
import com.ahmedsaadkenawy.covid19.repository.DataRepository;

import java.util.ArrayList;

public class DataViewModel extends ViewModel {


    private MutableLiveData<ArrayList<CountryData>> listMutableLiveData;
    private DataRepository repository;
    public void init(){
        if (listMutableLiveData!=null){
            return;
        }else
        {
            repository=DataRepository.getInstance();
            listMutableLiveData=repository.getData();
        }
    }
    public LiveData<ArrayList<CountryData>> getData(){
        return listMutableLiveData;
    }
}
