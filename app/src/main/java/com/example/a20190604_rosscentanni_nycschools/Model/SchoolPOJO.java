package com.example.a20190604_rosscentanni_nycschools.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Name: SchoolPOJO
 * Purpose: POJO for the response from the NYC School list service
 */
public class SchoolPOJO {

    @SerializedName("dbn")
    private String id;
    @SerializedName("school_name")
    private String name;
    @SerializedName("borough")
    private String borough;

    /*
        GETTERS/SETTERS
     */

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBorough() {
        return borough;
    }

    public void setBorough(String borough) {
        this.borough = borough;
    }
}
