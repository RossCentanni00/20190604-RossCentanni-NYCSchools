package com.example.a20190604_rosscentanni_nycschools.View.SchoolInfo;

import com.example.a20190604_rosscentanni_nycschools.Model.ScoresPOJO;

/**
 * Name: SchoolInfoView
 * Purpose: Contract between SchoolInfoFragment and SchoolInfoPresenter
 */
public interface SchoolInfoView {
    void displayErrorToast();

    void populateSATScores(ScoresPOJO scores);
}
