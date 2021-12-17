package com.example.subproject_ma_14att_n7_baohaihaotutuong.remote;

import com.example.subproject_ma_14att_n7_baohaihaotutuong.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APICall {
//    @Headers({
//            "Accept: application/json",
//            "Content-Type: application/x-www-form-urlencoded",
//    })
    @POST("login")
    Call<User> userLogin(@Body User user);


}
