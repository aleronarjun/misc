package com.example.android.sampleproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.sampleproject.model.Country;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arjun Vidyarthi on 26-Apr-18.
 */

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryAdapterViewHolder> {
    private String LOG_TAG;
    private Context activity_context;
    private List<Country> countryList;



    private final CountryAdapterOnClickHandler handler;

    public interface CountryAdapterOnClickHandler{
        void onClick(Country country);
    }

    public CountryAdapter(Context context, CountryAdapterOnClickHandler handler){

        activity_context = context;
        this.handler = handler;
    }

    public void setCountryList(List<Country> countryList){
        this.countryList = countryList;
    }

    @Override
    public CountryAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.item;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutIdForListItem, parent, false);

        return new CountryAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CountryAdapterViewHolder holder, int position) {
        if(countryList == null){
            holder.flag.setImageResource(R.mipmap.ic_launcher);
        }
        else{
            String currentFlagURL = countryList.get(position).getFlag();
            Picasso.with(activity_context).load(currentFlagURL).into(holder.flag);
        }
    }

    @Override
    public int getItemCount() {
        if(countryList == null){
            return 0;
        }
        return countryList.size();
    }

    public class CountryAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView flag;

        public CountryAdapterViewHolder(View itemView){
            super(itemView);
            flag = itemView.findViewById(R.id.flagimage);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
                int adapterPosition = getAdapterPosition();
                Country dataForThisCountry = countryList.get(adapterPosition);
                handler.onClick(dataForThisCountry);
        }
    }
}
