package com.example.a20190604_rosscentanni_nycschools.Presenter;

import com.example.a20190604_rosscentanni_nycschools.Model.ScoresPOJO;
import com.example.a20190604_rosscentanni_nycschools.View.SchoolInfo.SchoolInfoView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

/**
 * Name: SchoolInfoPresenterTest
 * Purpose: Test class for the SchoolInfoPresenter
 */
public class SchoolInfoPresenterTest {

    SchoolInfoPresenter mSchoolInfoPresenter;

    @Mock
    SchoolInfoView mockSchoolInfoView;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        mSchoolInfoPresenter = new SchoolInfoPresenter(mockSchoolInfoView);
    }

    //getSATScores is problematic to test at the moment due to the getInstance method being
    //static. Dependency injection would clear this up.

    /**
     * Verifies that the presenter fills in data for an empty service response correctly
     * and passes it to the View.
     */
    @Test
    public void onRequestSuccess_fills_in_missing_information_for_empty_result_and_passes_to_view(){
        ScoresPOJO expectedResult = new ScoresPOJO();
        expectedResult.setMathSATScore("NO DATA FOUND FOR THIS SCHOOL");
        expectedResult.setWritingSATScore("NO DATA FOUND FOR THIS SCHOOL");
        expectedResult.setReadingSATScore("NO DATA FOUND FOR THIS SCHOOL");

        List<ScoresPOJO> emptyList = new ArrayList<>();
        mSchoolInfoPresenter.onRequestSuccess(emptyList);
        verify(mockSchoolInfoView).populateSATScores(eq(expectedResult));
    }

    /**
     * Verifies that the presenter fills in any missing data for a non-empty response and passes
     * it to the View correctly.
     */
    @Test
    public void onRequestSuccess_fills_in_any_missing_information_for_non_empty_result_and_passes_to_view(){
        ScoresPOJO expectedResult = new ScoresPOJO();
        expectedResult.setMathSATScore("111");
        expectedResult.setWritingSATScore("UNKNOWN SCORE");
        expectedResult.setReadingSATScore("UNKNOWN SCORE");

        List<ScoresPOJO> dummyResponse = new ArrayList<>();
        ScoresPOJO dummyScores = new ScoresPOJO();
        dummyScores.setMathSATScore("111");
        dummyScores.setWritingSATScore("");
        dummyScores.setReadingSATScore("s");
        dummyResponse.add(dummyScores);

        mSchoolInfoPresenter.onRequestSuccess(dummyResponse);
        verify(mockSchoolInfoView).populateSATScores(expectedResult);
    }

    @Test
    public void onRequestFailure_tells_view_to_display_error(){
        mSchoolInfoPresenter.onRequestFailure();
        verify(mockSchoolInfoView).displayErrorToast();
    }
}