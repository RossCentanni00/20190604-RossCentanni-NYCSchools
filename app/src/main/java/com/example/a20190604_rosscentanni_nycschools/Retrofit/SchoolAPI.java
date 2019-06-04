package com.example.a20190604_rosscentanni_nycschools.Retrofit;

import com.example.a20190604_rosscentanni_nycschools.Model.SchoolPOJO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SchoolAPI {

    @GET("s3k6-pzi2.json")
    Call<List<SchoolPOJO>> schoolListRequest();
}
