package com.example.a20190604_rosscentanni_nycschools.Presenter;

import com.example.a20190604_rosscentanni_nycschools.Model.SchoolPOJO;
import com.example.a20190604_rosscentanni_nycschools.Retrofit.MyRetrofitClient;
import com.example.a20190604_rosscentanni_nycschools.Retrofit.SchoolListRequestCallback;
import com.example.a20190604_rosscentanni_nycschools.View.SchoolList.SchoolListView;

import java.util.List;

/**
 * Name: SchoolListPresenter
 * Purpose: Presenter for the School List screen
 */
public class SchoolListPresenter implements SchoolListRequestCallback {

    private SchoolListView mSchoolListView;

    /**
     * Constructor
     * @param view an implementation of SchoolListView
     */
    public SchoolListPresenter(SchoolListView view){
        mSchoolListView = view;
    }

    /*
        PUBLIC METHODS
     */

    /**
     * Queries the Retrofit client for a list of current NYC schools.
     */
    public void getListOfSchools() {
        MyRetrofitClient.getInstance().schoolListRequest(this);
    }

    /**
     * Fired off by Retrofit client when request is successful, passes the list of schools
     * to the view.
     * @param schoolList a list of NYC schools, list should never be empty.
     */
    @Override
    public void onRequestSuccess(List<SchoolPOJO> schoolList) {
        mSchoolListView.populateListOfSchools(schoolList);
    }

    /**
     * Fired off by Retrofit client when request fails for any reason.
     */
    @Override
    public void onRequestFailure() {
        mSchoolListView.displayErrorToast();
    }
}
