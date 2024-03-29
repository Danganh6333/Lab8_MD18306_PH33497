package com.dangchph33497.fpoly.lab8_md18306_ph33497.Service;

import com.dangchph33497.fpoly.lab8_md18306_ph33497.Models.District;
import com.dangchph33497.fpoly.lab8_md18306_ph33497.Models.DistrictRequest;
import com.dangchph33497.fpoly.lab8_md18306_ph33497.Models.GHNCancelRequest;
import com.dangchph33497.fpoly.lab8_md18306_ph33497.Models.GHNCancelResponse;
import com.dangchph33497.fpoly.lab8_md18306_ph33497.Models.GHNOrderRequest;
import com.dangchph33497.fpoly.lab8_md18306_ph33497.Models.GHNOrderResponse;
import com.dangchph33497.fpoly.lab8_md18306_ph33497.Models.Order;
import com.dangchph33497.fpoly.lab8_md18306_ph33497.Models.Province;
import com.dangchph33497.fpoly.lab8_md18306_ph33497.Models.ResponseGHN;
import com.dangchph33497.fpoly.lab8_md18306_ph33497.Models.Ward;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GHNServices {
    public static String GHN_URL = "https://dev-online-gateway.ghn.vn/";

    //GET dach sách TP/Tỉnh
    @GET("shiip/public-api/master-data/province")
    Call<ResponseGHN<ArrayList<Province>>> getListProvince();
    //Get danh sách Quận/Huyện
    @POST("shiip/public-api/master-data/district")
    Call<ResponseGHN<ArrayList<District>>> getListDistrict(@Body DistrictRequest districtRequest);
    //Get danh sách Phường/Xã
    @GET("shiip/public-api/master-data/ward")
    Call<ResponseGHN<ArrayList<Ward>>> getListWard(@Query("district_id") int district_id);
    @POST("shiip/public-api/v2/shipping-order/create")
    Call<ResponseGHN<GHNOrderResponse>>GHNOrder(@Body GHNOrderRequest ghnOrderRequest);
    @POST("shiip/public-api/v2/switch-status/cancel")
    Call<ResponseGHN<ArrayList<GHNCancelResponse>>> cancelOrder(@Body GHNCancelRequest cancelRequest);

}
