package com.androindian.finance;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface FinanceInterafce {

    @Headers("Content-Type:application/json")
    @POST("api.php")
    Call<RegResponse> CreateUser(@Body JsonObject jsonObject);

    @Headers("Content-Type:application/json")
    @POST("api.php")
    Call<LoginResponse> LoginUser(@Body JsonObject jsonObject);

    @Headers("Content-Type:application/json")
    @POST("api.php")
    Call<AddmanResponse> addman(@Body JsonObject jsonObject);

    @Headers("Content-Type:application/json")
    @POST("api.php")
    Call<ManlistRes> manlist(@Body JsonObject jsonObject);

    @Headers("Content-Type:application/json")
    @POST("api.php")
    Call<AddGroupRes> addgroup(@Body JsonObject jsonObject);
}
