package com.example.a20190604_rosscentanni_nycschools.View.SchoolList;

import com.example.a20190604_rosscentanni_nycschools.Model.SchoolPOJO;

import java.util.List;

/**
 * Name: SchoolListView
 * Purpose: Contract between SchoolList presenter and fragment
 */
public interface SchoolListView {
    void populateListOfSchools(List<SchoolPOJO> schoolList);

    void displayErrorToast();
}
