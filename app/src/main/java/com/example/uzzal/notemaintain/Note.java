package com.example.uzzal.notemaintain;

import android.content.Context;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Note implements Serializable {

    private long mDateTime;
    private String mTitle;
    private String mContent;

    public Note(long dateTime, String title, String content) {
        this.mDateTime = dateTime;
        this.mTitle = title;
        this.mContent = content;
    }

    public long getmDateTime() {
        return mDateTime;
    }

    public void setmDateTime(long mDateTime) {
        this.mDateTime = mDateTime;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmContent() {
        return mContent;
    }

    public void setmContent(String mContent) {
        this.mContent = mContent;
    }

    public String getDateTimeFormatted(Context context){
        SimpleDateFormat sdf  = new SimpleDateFormat("dd/MM/yyyy  -  HH:mm:ss"
                , context.getResources().getConfiguration().locale);
        sdf.setTimeZone(TimeZone.getDefault());
        return sdf.format(new Date(mDateTime));
    }
}
