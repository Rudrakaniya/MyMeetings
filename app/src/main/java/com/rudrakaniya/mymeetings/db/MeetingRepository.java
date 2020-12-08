package com.rudrakaniya.mymeetings.db;

import android.app.Application;

import com.rudrakaniya.mymeetings.entity.Meeting;

import java.util.List;

public class MeetingRepository {

    private MeetingsDao meetingsDao;
//    private LiveData<List<Word>> mAllWords;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples

    public MeetingRepository(Application application) {
        MeetingsRoomDatabase db = MeetingsRoomDatabase.getInstance(application);
        meetingsDao = db.meetingsDao();
//        mAllWords = mWordDao.getAlphabetizedWords();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
//    LiveData<List<Word>> getAllWords() {
//        return mAllWords;
//    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
   public void insert(Meeting meeting) {
        MeetingsRoomDatabase.databaseWriteExecutor.execute(() -> {
            meetingsDao.insertMeeting(meeting);
        });
    }

    public List<Meeting> getAllMeetings() {
        return meetingsDao.getAllMeetings();
    };


}
