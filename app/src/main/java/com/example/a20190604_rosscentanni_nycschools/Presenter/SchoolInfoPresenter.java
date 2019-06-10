package com.example.a20190604_rosscentanni_nycschools.Presenter;

import com.example.a20190604_rosscentanni_nycschools.Model.ScoresPOJO;
import com.example.a20190604_rosscentanni_nycschools.Retrofit.MyRetrofitClient;
import com.example.a20190604_rosscentanni_nycschools.Retrofit.SchoolInfoRequestCallback;
import com.example.a20190604_rosscentanni_nycschools.View.SchoolInfo.SchoolInfoView;

import java.util.List;

/**
 * Name: SchoolListPresenter
 * Purpose: Presenter for the School Info screen
 */
public class SchoolInfoPresenter implements SchoolInfoRequestCallback {
    private SchoolInfoView mSchoolInfoView;

    /**
     * Constructor
     *
     * @param view an implementation of SchoolInfoView
     */
    public SchoolInfoPresenter(SchoolInfoView view) {
        mSchoolInfoView = view;
    }

    /*
        PUBLIC METHODS
     */

    /**
     * Passes dbKey to Retrofit cvlient so that it may get a given school's test scores
     *
     * @param dbKey the school's dbKey
     */
    public void getSATScores(String dbKey) {
        MyRetrofitClient.getInstance().schoolInfoRequest(this, dbKey);
    }

    /**
     * Fired off by Retrofit client when request is successful, fills in any missing
     * data and passes it along to the view.
     *
     * @param scores SAT scores for a given school, in a ScoresPOJO List of either 0 or 1 size.
     */
    @Override
    public void onRequestSuccess(List<ScoresPOJO> scores) {
        ScoresPOJO result;

        //Since the service can return an empty list, we need to take that into account.
        if (scores.isEmpty()) {
            result = fillInMissingScoreInformation(null);
        } else {
            result = fillInMissingScoreInformation(scores.get(0));
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

    /*
        PRIVATE METHODS
     */

    /**
     * Some schools aren't present in the SAT Scores database, or have "s" for certain scores.
     * This method fills in any missing info with an appropriate string. Since I could not find
     * anything explaining what that "s" represents, I gave it a distinct message.
     *
     * @param rawScoreData An object of type ScoresPOJO, it will either be pre-populated
     *                     or it will be a null object.
     */
    private ScoresPOJO fillInMissingScoreInformation(ScoresPOJO rawScoreData) {
        ScoresPOJO filledInData;
        String fillerString;
        final String NO_DATA_FOUND = "NO DATA FOUND FOR THIS SCHOOL";
        final String UNKNOWN_DATA = "UNKNOWN SCORE";

        if (rawScoreData == null) {
            filledInData = new ScoresPOJO();
            fillerString = NO_DATA_FOUND;
        } else {
            filledInData = rawScoreData;
            fillerString = UNKNOWN_DATA;
        }

        if (isStringEmptyOrTheLetterS(filledInData.getWritingSATScore())) {
            filledInData.setWritingSATScore(fillerString);
        }
        if (isStringEmptyOrTheLetterS(filledInData.getReadingSATScore())) {
            filledInData.setReadingSATScore(fillerString);
        }
        if (isStringEmptyOrTheLetterS(filledInData.getMathSATScore())) {
            filledInData.setMathSATScore(fillerString);
        }

        return filledInData;
    }

    /**
     * Helper method for fillInMissingScoreInformation to simplify the conditionals
     *
     * @param s any string
     * @return true if the string is "s", "", or null, false otherwise
     */
    private boolean isStringEmptyOrTheLetterS(String s) {
        return ("s".equalsIgnoreCase(s) ||
                s == null ||
                "".equalsIgnoreCase(s));
    }
}
