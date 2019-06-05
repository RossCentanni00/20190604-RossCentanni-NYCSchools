package com.example.a20190604_rosscentanni_nycschools.Retrofit;

import com.example.a20190604_rosscentanni_nycschools.Model.SchoolPOJO;
import com.example.a20190604_rosscentanni_nycschools.Model.ScoresPOJO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Name: SchoolAPI
 * Purpose: Retrofit API for NYC School data service calls.
 */
public interface SchoolAPI {

    @GET("s3k6-pzi2.json")
    Call<List<SchoolPOJO>> schoolListRequest();

    @GET("f9bf-2cp4.json")
    Call<List<ScoresPOJO>> schoolInfoRequest(@Query("dbn") String dbKey);
}
