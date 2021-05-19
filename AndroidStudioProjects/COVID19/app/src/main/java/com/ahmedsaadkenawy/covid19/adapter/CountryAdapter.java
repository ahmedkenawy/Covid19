package com.ahmedsaadkenawy.covid19.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmedsaadkenawy.covid19.R;
import com.ahmedsaadkenawy.covid19.model.CountryData;
import com.bumptech.glide.Glide;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Map;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {
    private Context context;
    private ArrayList<CountryData> data;

    public CountryAdapter(Context context, ArrayList<CountryData> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        CoutryItemLayoutBinding binding = DataBindingUtil.inflate(
//                LayoutInflater.from(context), R.layout.coutry_item_layout, parent, false);
//        return new CountryViewHolder(binding);

        View view=LayoutInflater.from(context).inflate(R.layout.coutry_item_layout,parent,false);
        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        CountryData countryData = data.get(position);

        holder.tv1.setText(String.valueOf(position + 1));
        holder.tv3.setText(NumberFormat.getInstance().format(Integer.parseInt(countryData.getCases())));
        holder.tv2.setText(countryData.getCountry());
        Map<String, String> img = countryData.getCountryInfo();
        Glide.with(context).load(img.get("flag")).into(holder.view);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class CountryViewHolder extends RecyclerView.ViewHolder {
        TextView tv1,tv2,tv3;
        ImageView view;
        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            tv1=itemView.findViewById(R.id.sno);
            tv2=itemView.findViewById(R.id.countryName);
            tv3=itemView.findViewById(R.id.totalCases);
            view=itemView.findViewById(R.id.countryImage);
        }
    }
}
