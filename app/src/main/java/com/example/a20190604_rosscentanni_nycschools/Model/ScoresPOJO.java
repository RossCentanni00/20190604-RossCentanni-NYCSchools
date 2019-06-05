package com.example.a20190604_rosscentanni_nycschools.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Name: ScoresPOJO
 * Purpose: POJO for the response from the SAT score service
 */
public class ScoresPOJO {

    @SerializedName("sat_critical_reading_avg_score")
    private String readingSATScore;
    @SerializedName("sat_math_avg_score")
    private String mathSATScore;
    @SerializedName("sat_writing_avg_score")
    private String writingSATScore;

    /*
        GETTERS/SETTERS
     */

    public String getReadingSATScore() {
        return readingSATScore;
    }

    public void setReadingSATScore(String readingSATScore) {
        this.readingSATScore = readingSATScore;
    }

    public String getMathSATScore() {
        return mathSATScore;
    }

    public void setMathSATScore(String mathSATScore) {
        this.mathSATScore = mathSATScore;
    }

    public String getWritingSATScore() {
        return writingSATScore;
    }

    public void setWritingSATScore(String writingSATScore) {
        this.writingSATScore = writingSATScore;
    }
}
