package com.example.appbanlinhkien.retrofit;

import android.database.Observable;

import com.example.appbanlinhkien.model.CategoryModel;

import retrofit2.http.GET;

public interface ApiLinhKien {
    @GET("getloaisp.php")
    Observable<CategoryModel> getCategory();
}
