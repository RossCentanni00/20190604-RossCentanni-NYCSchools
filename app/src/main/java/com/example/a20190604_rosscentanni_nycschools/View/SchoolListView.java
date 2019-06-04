package com.example.a20190604_rosscentanni_nycschools.View;

import com.example.a20190604_rosscentanni_nycschools.Model.SchoolPOJO;

import java.util.List;

/**
 * Name: SchoolListView
 * Purpose: Interface for communication between SchoolList presenter and fragment
 */
public interface SchoolListView {
    void populateListOfSchools(List<SchoolPOJO> schoolList);

    void displayErrorToast();
}
