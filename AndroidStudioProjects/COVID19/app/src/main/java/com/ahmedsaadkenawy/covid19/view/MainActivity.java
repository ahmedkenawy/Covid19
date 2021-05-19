package com.ahmedsaadkenawy.covid19.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.ahmedsaadkenawy.covid19.R;
import com.ahmedsaadkenawy.covid19.databinding.ActivityMainBinding;
import com.ahmedsaadkenawy.covid19.model.CountryData;

import org.eazegraph.lib.models.PieModel;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    DataViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        model = new ViewModelProvider(this).get(DataViewModel.class);
        model.init();
        model.getData().observe(this, new Observer<ArrayList<CountryData>>() {
            @Override
            public void onChanged(ArrayList<CountryData> countryData) {
                for (int i = 0; i < countryData.size(); i++) {
                    if (countryData.get(i).getCountry().equals("Egypt")) {
                        int confirm = Integer.parseInt(countryData.get(i).getCases());
                        int todayCases = Integer.parseInt(countryData.get(i).getTodayCases());
                        int deaths = Integer.parseInt(countryData.get(i).getDeaths());
                        int todayDeaths = Integer.parseInt(countryData.get(i).getTodayDeaths());
                        int recovered = Integer.parseInt(countryData.get(i).getRecovered());
                        int todayRecovered = Integer.parseInt(countryData.get(i).getTodayRecovered());
                        int tests = Integer.parseInt(countryData.get(i).getTests());
                        int active = Integer.parseInt(countryData.get(i).getActive());
                        binding.totalConfirm.setText(NumberFormat.getInstance().format(confirm));
                        binding.totalActive.setText(NumberFormat.getInstance().format(active));
                        binding.todayConfirm.setText("(+" + NumberFormat.getInstance().format(todayCases) + ")");
                        binding.totalDeath.setText(NumberFormat.getInstance().format(deaths));
                        binding.todayDeath.setText("(+" + NumberFormat.getInstance().format(todayDeaths) + ")");
                        binding.totalRecovered.setText(NumberFormat.getInstance().format(recovered));
                        binding.todayRecovered.setText("(+" + NumberFormat.getInstance().format(todayRecovered) + ")");
                        binding.totalTests.setText(NumberFormat.getInstance().format(tests));
                        setText(countryData.get(i).getUpdated());
                        binding.pieChart.addPieSlice(new PieModel("Confirm",confirm,getResources().getColor(R.color.yellow)));
                        binding.pieChart.addPieSlice(new PieModel("Active",active,getResources().getColor(R.color.blue_pie)));
                        binding.pieChart.addPieSlice(new PieModel("Recovered",recovered,getResources().getColor(R.color.green_pie)));
                        binding.pieChart.addPieSlice(new PieModel("Deaths",deaths,getResources().getColor(R.color.red_pie)));
                        binding.pieChart.startAnimation();

                    }
                }
            }
        });

        binding.swap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),CountryActivity.class);
                startActivity(intent);
            }
        });
    }
private void setText(String Updated){
    DateFormat format=new SimpleDateFormat("MMM dd, yyyy");
    long milliSeconds=Long.parseLong(Updated);
    Calendar calendar=Calendar.getInstance();
    calendar.setTimeInMillis(milliSeconds);
    binding.timeUpdated.setText("Updated at "+format.format((calendar.getTime())));
}
}