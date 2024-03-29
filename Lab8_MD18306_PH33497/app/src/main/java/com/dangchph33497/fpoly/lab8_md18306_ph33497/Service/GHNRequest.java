package com.dangchph33497.fpoly.lab8_md18306_ph33497.Service;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GHNRequest {
    //Shop ID của tài khaonr GHN
    public final static String SHOPID = "2507324";
    public final static String TokenGHN = "b2cead09-ecf4-11ee-a6e6-e60958111f48";
    private GHNServices ghnRequestInterface;

    public GHNRequest() {
        //Tạo Intercept
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @NonNull
            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {
                Request request = chain.request().newBuilder().addHeader("ShopId",SHOPID)
                        .addHeader("Token",TokenGHN).build();
                return chain.proceed(request);
            }
        });
        //Create Retrofit
        ghnRequestInterface = new Retrofit.Builder().baseUrl(GHNServices.GHN_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build().create(GHNServices.class);
    }
    public GHNServices callAPI(){
        //Get Retrofit
        return ghnRequestInterface;
    }

}
