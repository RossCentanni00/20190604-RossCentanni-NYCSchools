package com.example.a20190604_rosscentanni_nycschools.Retrofit;

import com.example.a20190604_rosscentanni_nycschools.Model.SchoolPOJO;

import java.util.List;

/**
 * Name: SchoolListRequestCallback
 * Purpose: Interface for communication between the Retrofit client and SchoolListPresenter
 */
public interface SchoolListRequestCallback {
    void onRequestSuccess(List<SchoolPOJO> schoolList);

    void onRequestFailure();

}
