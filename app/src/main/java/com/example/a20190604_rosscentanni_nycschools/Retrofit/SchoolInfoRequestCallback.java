package com.example.a20190604_rosscentanni_nycschools.Retrofit;

import com.example.a20190604_rosscentanni_nycschools.Model.ScoresPOJO;

import java.util.List;

public interface SchoolInfoRequestCallback {
        void onRequestSuccess(List<ScoresPOJO> scores);
        void onRequestFailure();
}
