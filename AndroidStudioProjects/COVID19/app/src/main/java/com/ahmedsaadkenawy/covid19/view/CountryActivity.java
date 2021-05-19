package com.ahmedsaadkenawy.covid19.view;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmedsaadkenawy.covid19.R;
import com.ahmedsaadkenawy.covid19.adapter.CountryAdapter;
import com.ahmedsaadkenawy.covid19.model.ApiUtilities;
import com.ahmedsaadkenawy.covid19.model.CountryData;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountryActivity extends AppCompatActivity {

    DataViewModel model = new DataViewModel();
    ArrayList<CountryData> data;
    CountryAdapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);
        model = new ViewModelProvider(this).get(DataViewModel.class);
        model.init();
        data = new ArrayList<>();
        recyclerView = findViewById(R.id.countries);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);

        adapter = new CountryAdapter(getApplicationContext(), data);
        recyclerView.setAdapter(adapter);

        ApiUtilities.getApiInterface().getData().enqueue(new Callback<ArrayList<CountryData>>() {
            @Override
            public void onResponse(Call<ArrayList<CountryData>> call, Response<ArrayList<CountryData>> response) {
                data.addAll(response.body());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArrayList<CountryData>> call, Throwable t) {

                Toast.makeText(CountryActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

    }
}