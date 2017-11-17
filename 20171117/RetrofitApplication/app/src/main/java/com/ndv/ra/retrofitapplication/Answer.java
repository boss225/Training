package com.ndv.ra.retrofitapplication;

import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 11/17/2017.
 */

public class Answer {
    @SerializedName("answer_id")
    public int answerId;

    @SerializedName("is_accepted")
    public boolean accepted;

    public int score;

    @Override
    public String toString() {
        return answerId + " - Score: " + score + " - Accepted: " + (accepted ? "Yes" : "No");
    }
}
