package com.example.a20190604_rosscentanni_nycschools.Presenter;

import com.example.a20190604_rosscentanni_nycschools.Model.ScoresPOJO;
import com.example.a20190604_rosscentanni_nycschools.Retrofit.MyRetrofitClient;
import com.example.a20190604_rosscentanni_nycschools.Retrofit.SchoolInfoRequestCallback;
import com.example.a20190604_rosscentanni_nycschools.View.SchoolInfoView;

import java.util.List;

/**
 * Name: SchoolListPresenter
 * Purpose: Presenter for the School Info screen
 */
public class SchoolInfoPresenter implements SchoolInfoRequestCallback {

    private SchoolInfoView mSchoolInfoView;

    /**
     * Constructor
     * @param view an implementation of SchoolInfoView
     */
    public SchoolInfoPresenter(SchoolInfoView view){
        mSchoolInfoView = view;
    }



    /**
     * Fired off by Retrofit client when request is successful
     * @param scores SAT scores for a given school
     */
    @Override
    public void onRequestSuccess(List<ScoresPOJO> scores) {
        ScoresPOJO result;
        final String UNKNOWN_RESULT = "UNKNOWN";

        //If service returned empty list, populate unknown.
        if(scores.isEmpty()){
            result = new ScoresPOJO();
            result.setSchoolName("RESULTS NOT FOUND");
            result.setMathSATScore(UNKNOWN_RESULT);
            result.setReadingSATScore(UNKNOWN_RESULT);
            result.setWritingSATScore(UNKNOWN_RESULT);
        } else{
            result = scores.get(0);
            //Some schools have a value of 's' for their scores, replace that with UNKNOWN.
            if("s".equalsIgnoreCase(result.getMathSATScore())){
                result.setMathSATScore(UNKNOWN_RESULT);
            }
            if("s".equalsIgnoreCase(result.getReadingSATScore())){
                result.setReadingSATScore(UNKNOWN_RESULT);
            }
            if("s".equalsIgnoreCase(result.getWritingSATScore())){
                result.setWritingSATScore(UNKNOWN_RESULT);
            }
        }

        mSchoolInfoView.populateSATScores(result);
    }

    /**
     * Fired off by Retrofit client when request fails for any reason.
     */
    @Override
    public void onRequestFailure() {
        mSchoolInfoView.displayErrorToast();
    }

    /**
     * Passes dbKey to Retrofit Client so that it may get a given school's test scores
     * @param dbKey the school's dbKey
     */
    public void getSATScores(String dbKey) {
        MyRetrofitClient.getInstance().schoolInfoRequest(this, dbKey);
    }
}
