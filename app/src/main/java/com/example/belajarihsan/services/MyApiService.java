package com.example.belajarihsan.services;

import com.example.belajarihsan.model.UserDataResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MyApiService {
   @GET("api/users")
   Call<UserDataResponse> getUserData(@Query("page") int page, @Query("per_page") int perPage);
}
