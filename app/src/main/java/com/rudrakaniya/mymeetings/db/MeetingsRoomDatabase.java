package com.rudrakaniya.mymeetings.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.rudrakaniya.mymeetings.entity.Meeting;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Meeting.class}, version = 1)
public abstract class MeetingsRoomDatabase extends RoomDatabase {

    public abstract MeetingsDao meetingsDao();
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    //Making a Singleton
    private static volatile MeetingsRoomDatabase INSTANCE;

    public static MeetingsRoomDatabase getInstance(Context context){
        if (INSTANCE == null){
            synchronized (MeetingsRoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MeetingsRoomDatabase.class, "Meetings_Database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
