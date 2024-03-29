package com.dangchph33497.fpoly.lab8_md18306_ph33497.Models;

public class District {
    private int DistrictID;
    private int ProvinceID;
    private  String DistrictName;

    public District(int districtID, int provinceID, String districtName) {
        DistrictID = districtID;
        ProvinceID = provinceID;
        DistrictName = districtName;
    }

    public int getDistrictID() {
        return DistrictID;
    }

    public void setDistrictID(int districtID) {
        DistrictID = districtID;
    }

    public int getProvinceID() {
        return ProvinceID;
    }

    public void setProvinceID(int provinceID) {
        ProvinceID = provinceID;
    }

    public String getDistrictName() {
        return DistrictName;
    }

    public void setDistrictName(String districtName) {
        DistrictName = districtName;
    }
}

