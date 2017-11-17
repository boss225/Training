package com.ndv.ra.retrofitapplication;

import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 11/17/2017.
 */

public class Question {
    String title;
    String body;

    @SerializedName("question_id")
    String questionId;

    @Override
    public String toString() {
        return(title);
    }
}
