package com.rudrakaniya.mymeetings.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;

@Entity(tableName = "meetings_table")
public class Meeting {


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "meeting_uid")
    private int uid;

    @ColumnInfo(name = "meeting_title")
    private String meetingTitle;

    @ColumnInfo(name = "is_online")
    private boolean isOnline;

    @ColumnInfo(name = "platform")
    private String platform;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "url")
    private String url;

    @ColumnInfo(name = "id")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @ColumnInfo(name = "password")
    private String password;

    @ColumnInfo(name = "original_message")
    private String originalMessage;

    public Meeting() {
    }

    public Meeting(String meetingTitle, boolean isOnline, String platform, String date, String url, String id, String password, String originalMessage) {
        this.meetingTitle = meetingTitle;
        this.isOnline = isOnline;
        this.platform = platform;
        this.date = date;
        this.url = url;
        this.id = id;
        this.password = password;
        this.originalMessage = originalMessage;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getMeetingTitle() {
        return meetingTitle;
    }

    public void setMeetingTitle(String meetingTitle) {
        this.meetingTitle = meetingTitle;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOriginalMessage() {
        return originalMessage;
    }

    public void setOriginalMessage(String originalMessage) {
        this.originalMessage = originalMessage;
    }

    @Override
    public String toString() {
        return "myMeetingsEntity{" +
                "uid=" + uid +
                ", meetingTitle='" + meetingTitle + '\'' +
                ", isOnline=" + isOnline +
                ", platform='" + platform + '\'' +
                ", date=" + date +
                ", url='" + url + '\'' +
                ", password='" + password + '\'' +
                ", originalMessage='" + originalMessage + '\'' +
                '}' + "\n";
    }

}
