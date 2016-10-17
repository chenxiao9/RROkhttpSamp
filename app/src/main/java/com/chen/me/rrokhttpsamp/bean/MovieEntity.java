package com.chen.me.rrokhttpsamp.bean;

import com.chen.me.rrokhttpsamp.bean.Movie.Subject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2016/10/13 0013.
 */

public class MovieEntity {
    @SerializedName("count")
    @Expose
    private int count;

    @SerializedName("start")
    @Expose
    private int start;

    @SerializedName("total")
    @Expose
    private int total;

    @SerializedName("subjects")
    @Expose
    private List<Subject> subjects;

    @SerializedName("title")
    @Expose
    private String title;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
