package com.example.televisionshowlist;

import java.util.Date;
import java.util.UUID;

public class Show {

    private UUID mId;
    private String mTitle;
    private Date mDate;
    private Date mTime;
    private boolean mWatched;
    private boolean mRecommend;

    public Show() {
        mId = UUID.randomUUID();
        mDate = new Date();
        mTime = new Date();
    }

    public UUID getId() { return mId; }

    public String getTitle() {
        return mTitle;
    }

    public Date getDate() {
        return mDate;
    }

    public Date getTime() { return mTime; }

    public boolean isWatched() { return mWatched; }

    public boolean isRecommend() { return mRecommend; }

    public void setId(UUID id) { mId = id; }

    public void setDate(Date date) {
        mDate = date;
    }

    public void setTime(Date time) { mTime = time; }

    public void setTitle(String title) { mTitle = title; }

    public void setWatched(boolean watched) { mWatched = watched; }

    public void setRecommend(boolean recommend) { mRecommend = recommend; }
}
