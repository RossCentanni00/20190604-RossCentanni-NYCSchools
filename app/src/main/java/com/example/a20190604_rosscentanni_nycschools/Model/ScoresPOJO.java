package com.example.a20190604_rosscentanni_nycschools.Model;

import com.google.gson.annotations.SerializedName;

public class ScoresPOJO {

    @SerializedName("school_name")
    private String schoolName;
    @SerializedName("sat_critical_reading_avg_score")
    private String readingSATScore;
    @SerializedName("sat_math_avg_score")
    private String mathSATScore;
    @SerializedName("sat_writing_avg_score")
    private String writingSATScore;

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

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
