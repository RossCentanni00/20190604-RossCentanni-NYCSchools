package com.example.a20190604_rosscentanni_nycschools.Retrofit;

import android.util.Log;

import com.example.a20190604_rosscentanni_nycschools.Model.SchoolPOJO;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyRetrofitClient {
    private static final String TAG = MyRetrofitClient.class.getSimpleName()+"_TAG";
    private static final String BASE_URL = "https://data.cityofnewyork.us/resource/";

    private static MyRetrofitClient instance;
    private Retrofit retrofit;
    private SchoolAPI schoolAPI;

    /**
     * Private constructor for singleton.
     */
    private MyRetrofitClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        schoolAPI = retrofit.create(SchoolAPI.class);
    }

    /**
     * Returns instance of MyRetrofitClient, creating a new one if one does not exist.
     */
    public static MyRetrofitClient getInstance(){
        if (instance == null){
            return new MyRetrofitClient();
        }
        else{
            return instance;
        }
    }

    /**
     * Queries for the list of NYC schools
     * @param callback A reference to the SchoolListPresenter
     */
    public void schoolListRequest(final SchoolListRequestCallback callback) {
        Call<List<SchoolPOJO>> call = schoolAPI.schoolListRequest();
        call.enqueue(new Callback<List<SchoolPOJO>>() {
            @Override
            public void onResponse(Call<List<SchoolPOJO>> call, Response<List<SchoolPOJO>> response) {
                if(response.isSuccessful()){
                    List<SchoolPOJO> schoolList = response.body();
                    callback.onRequestSuccess(schoolList);
                }else {
                    Log.d(TAG, response.errorBody().toString());
                    callback.onRequestFailure();
                }
            }

            @Override
            public void onFailure(Call<List<SchoolPOJO>> call, Throwable t) {
                Log.e(TAG, t.getMessage());
                callback.onRequestFailure();
            }
        });
    }
}