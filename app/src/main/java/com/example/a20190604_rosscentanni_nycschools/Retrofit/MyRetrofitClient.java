package com.example.a20190604_rosscentanni_nycschools.Retrofit;

import android.util.Log;

import com.example.a20190604_rosscentanni_nycschools.Model.SchoolPOJO;
import com.example.a20190604_rosscentanni_nycschools.Model.ScoresPOJO;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.internal.EverythingIsNonNull;

/**
 * Name: MyRetrofitClient
 * Purpose: Singleton class to handle all API calls using Retrofit
 */
public class MyRetrofitClient {
    private static final String TAG = MyRetrofitClient.class.getSimpleName() + "_TAG";
    private static final String BASE_URL = "https://data.cityofnewyork.us/resource/";

    private static MyRetrofitClient instance;
    private SchoolAPI schoolAPI;

    /**
     * Private constructor for singleton.
     */
    private MyRetrofitClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        schoolAPI = retrofit.create(SchoolAPI.class);
    }

    /**
     * Returns instance of MyRetrofitClient, creating a new one if one does not exist.
     */
    public static MyRetrofitClient getInstance() {
        if (instance == null) {
            instance = new MyRetrofitClient();
        }
        return instance;
    }

    /**
     * Queries for the list of NYC schools
     *
     * @param callback A reference to the SchoolListPresenter
     */
    public void schoolListRequest(final SchoolListRequestCallback callback) {
        Call<List<SchoolPOJO>> call = schoolAPI.schoolListRequest();
        call.enqueue(new Callback<List<SchoolPOJO>>() {
            @Override
            @EverythingIsNonNull
            public void onResponse(Call<List<SchoolPOJO>> call, Response<List<SchoolPOJO>> response) {
                if (response.isSuccessful()) {
                    List<SchoolPOJO> schoolList = response.body();
                    callback.onRequestSuccess(schoolList);
                } else {
                    if (response.errorBody() != null) {
                        Log.d(TAG, "schoolListRequest: " + response.errorBody().toString());
                    }
                    callback.onRequestFailure();
                }
            }

            @Override
            @EverythingIsNonNull
            public void onFailure(Call<List<SchoolPOJO>> call, Throwable t) {
                Log.e(TAG, t.getMessage());
                callback.onRequestFailure();
            }
        });
    }


    public void schoolInfoRequest(final SchoolInfoRequestCallback callback, String dbKey) {
        Log.d(TAG, dbKey);
        Call<List<ScoresPOJO>> call = schoolAPI.schoolInfoRequest(dbKey);
        call.enqueue(new Callback<List<ScoresPOJO>>() {
            @Override
            @EverythingIsNonNull
            public void onResponse(Call<List<ScoresPOJO>> call, Response<List<ScoresPOJO>> response) {
                if (response.isSuccessful()) {
                    List<ScoresPOJO> scores = response.body();
                    callback.onRequestSuccess(scores);
                } else {
                    if (response.errorBody() != null) {
                        Log.d(TAG, response.errorBody().toString());
                    }
                    callback.onRequestFailure();
                }
            }

            @Override
            @EverythingIsNonNull
            public void onFailure(Call<List<ScoresPOJO>> call, Throwable t) {
                Log.e(TAG, t.getMessage());
                callback.onRequestFailure();
            }
        });
    }
}
