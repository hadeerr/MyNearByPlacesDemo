

package com.example.mynearbyplacesdemo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mynearbyplacesdemo.models.FoursquareResults;
import com.example.mynearbyplacesdemo.views.MapsActivity;

import java.util.List;

public class PlacePickerAdapter extends RecyclerView.Adapter<PlacePickerAdapter.ViewHolder> {

     Context context;

    private List<FoursquareResults> results;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name, address,rating,distance;
        String id;
        double latitude,longitude;

        public ViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            name = (TextView)v.findViewById(R.id.placePickerItemName);
            address = (TextView)v.findViewById(R.id.placePickerItemAddress);
            rating = (TextView)v.findViewById(R.id.placePickerItemRating);
            distance = (TextView)v.findViewById(R.id.placePickerItemDistance);
        }


        @Override
        public void onClick(View v) {
            Context context = name.getContext();
            Intent i = new Intent(context, MapsActivity.class);
            i.putExtra("name", name.getText());
            i.putExtra("ID", id);
            i.putExtra("latitude", latitude);
            i.putExtra("longitude", longitude);
            context.startActivity(i);

        }
    }

    public PlacePickerAdapter(Context context, List<FoursquareResults> results) {
        this.context = context;
        this.results = results;
    }

    @Override
    public PlacePickerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_place_picker, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(results.get(position).venue.name);
        holder.address.setText(results.get(position).venue.location.address);
        holder.distance.setText(Integer.toString(results.get(position).venue.location.distance) + "m");
        holder.id = results.get(position).venue.id;
        holder.latitude = results.get(position).venue.location.lat;
        holder.longitude = results.get(position).venue.location.lng;
    }

    @Override
    public int getItemCount() {
        return results.size();
    }
}