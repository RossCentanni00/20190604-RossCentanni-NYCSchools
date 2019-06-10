package com.example.a20190604_rosscentanni_nycschools.Model;

import android.support.annotation.Nullable;

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

    /**
     * Overridden equals for Junits
     * @param obj arbitrary object
     * @return true if they are equal, false if not
     */
    @Override
    public boolean equals(@Nullable Object obj) {
        if(obj instanceof ScoresPOJO){
            ScoresPOJO temp = (ScoresPOJO) obj;
            return (this.mathSATScore.equals(temp.getMathSATScore()) &&
                    this.readingSATScore.equals(temp.getReadingSATScore()) &&
                    this.writingSATScore.equals(temp.writingSATScore));
        } else{
            return false;
        }
    }
}
