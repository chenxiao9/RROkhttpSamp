package com.chen.me.rrokhttpsamp.bean.Movie;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2016/10/13 0013.
 */

public class Rating {
    @SerializedName("max")
    @Expose
    private Integer max;
    @SerializedName("average")
    @Expose
    private Double average;
    @SerializedName("stars")
    @Expose
    private String stars;
    @SerializedName("min")
    @Expose
    private Integer min;

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }
}
