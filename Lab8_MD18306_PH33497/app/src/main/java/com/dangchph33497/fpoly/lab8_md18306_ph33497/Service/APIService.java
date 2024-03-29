package com.dangchph33497.fpoly.lab8_md18306_ph33497.Service;

import com.dangchph33497.fpoly.lab8_md18306_ph33497.Models.Order;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {
    public static String BASE_URL = "";
    @POST("add-order")
    Call<Response<Response<Order>>> order(@Body Order order);
    @GET("get-list-order")
    Call<Response<ArrayList<Order>>> getListOrder(@Query("id_user") String id_user);
    @DELETE("delete-order/{order_code}")
    Call<Response<Order>> deleteOrder(@Path("order_code") String order_code);
}
