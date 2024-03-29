package com.dangchph33497.fpoly.lab8_md18306_ph33497.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.dangchph33497.fpoly.lab8_md18306_ph33497.Models.District;
import com.dangchph33497.fpoly.lab8_md18306_ph33497.R;

import java.util.ArrayList;

public class Adapter_Item_District_Select_GHN extends ArrayAdapter<District> implements SpinnerAdapter {
    private ArrayList<District> districts;
    private Context context;

    public Adapter_Item_District_Select_GHN(ArrayList<District> districts, Context context) {
        super(context, R.layout.spinner_layout, districts);
        this.districts = districts;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.spinner_layout, parent, false);
        }
        TextView tvIndex = convertView.findViewById(R.id.tvIndex);
        TextView tvContent = convertView.findViewById(R.id.tvContent);

        District district = getItem(position);

        if (district != null) {
            tvIndex.setText(String.valueOf(position));
            tvContent.setText(district.getDistrictName());
        }

        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }
}
