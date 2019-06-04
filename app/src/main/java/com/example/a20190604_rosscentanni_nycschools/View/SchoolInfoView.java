package com.example.a20190604_rosscentanni_nycschools.View;

import com.example.a20190604_rosscentanni_nycschools.Model.ScoresPOJO;

public interface SchoolInfoView {
    void displayErrorToast();
    void populateSATScores(ScoresPOJO scores);
}
