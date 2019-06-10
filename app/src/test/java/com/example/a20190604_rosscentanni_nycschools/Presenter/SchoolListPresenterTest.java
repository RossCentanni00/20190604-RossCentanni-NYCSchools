package com.example.a20190604_rosscentanni_nycschools.Presenter;

import com.example.a20190604_rosscentanni_nycschools.Model.SchoolPOJO;
import com.example.a20190604_rosscentanni_nycschools.View.SchoolList.SchoolListView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;

/**
 * Name: SchoolListPresenterTest
 * Purpose: Unit test file for SchoolListPresenter
 */
public class SchoolListPresenterTest {

    private SchoolListPresenter mSchoolListPresenter;

    @Mock
    private SchoolListView mockSchoolListView;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        mSchoolListPresenter = new SchoolListPresenter(mockSchoolListView);
    }

    //getListOfSchools is problematic to test at the moment due to the getInstance method being
    //static. Dependency injection would clear this up.

    /**
     * Verifies onRequestSuccess passes correct values to view.
     */
    @Test
    public void onRequestSuccess_tells_view_to_populate_list(){
        List<SchoolPOJO> dummyList = new ArrayList<>();
        SchoolPOJO dummySchool = new SchoolPOJO();
        dummySchool.setId("AAAAAA");
        dummySchool.setName("Frank's House of Education");
        dummySchool.setBorough("BRONX");
        dummyList.add(dummySchool);

        mSchoolListPresenter.onRequestSuccess(dummyList);
        verify(mockSchoolListView).populateListOfSchools(dummyList);
    }

    /**
     * Verifies onRequestFailure tells View to display a Toast
     */
    @Test
    public void onRequestFailure_tells_view_to_show_error(){
        mSchoolListPresenter.onRequestFailure();
        verify(mockSchoolListView).displayErrorToast();
    }

}