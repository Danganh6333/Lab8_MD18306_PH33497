package com.dangchph33497.fpoly.lab8_md18306_ph33497.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.dangchph33497.fpoly.lab8_md18306_ph33497.Models.Province;
import com.dangchph33497.fpoly.lab8_md18306_ph33497.Models.Ward;
import com.dangchph33497.fpoly.lab8_md18306_ph33497.R;

import java.util.ArrayList;

public class Adapter_Item_Ward_Select_GHN extends ArrayAdapter<Ward> implements SpinnerAdapter {
    private ArrayList<Ward> wards;
    private Context context;

    public  Adapter_Item_Ward_Select_GHN(@NonNull Context context, ArrayList<Ward> wards) {
        super(context, R.layout.spinner_layout_ward, wards);
        this.wards = wards;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.spinner_layout_ward, parent, false);
        }
        TextView tvIndex = convertView.findViewById(R.id.tvIndex);
        TextView tvContent = convertView.findViewById(R.id.tvContent);

        Ward ward = getItem(position);

        if (ward != null) {
            tvIndex.setText(String.valueOf(position));
            tvContent.setText(ward.getWardName());
        }

        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }
}
