package com.rudrakaniya.mymeetings.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.rudrakaniya.mymeetings.entity.Meeting;

import java.util.List;

@Dao
public interface MeetingsDao {

    @Insert
    void insertMeeting(Meeting meeting);

    @Query("SELECT * FROM meetings_table")
    List<Meeting> getAllMeetings();

    @Query("SELECT * FROM meetings_table")
    LiveData<List<Meeting>> findAllMeetings();

    @Query("SELECT * FROM meetings_table WHERE meeting_uid LIKE :uid")
    Meeting findMeetingById(int uid);

    @Delete
    void deleteMeeting(Meeting meeting);
}
