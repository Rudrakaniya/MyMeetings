package com.rudrakaniya.mymeetings.viewModel;

import android.app.Application;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.rudrakaniya.mymeetings.Section;
import com.rudrakaniya.mymeetings.db.MeetingsRoomDatabase;
import com.rudrakaniya.mymeetings.entity.Meeting;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MeetingViewModel extends AndroidViewModel {

    public static final String TAG = "MeetingViewModel";
    MeetingsRoomDatabase meetingsRoomDatabase;

    public MeetingViewModel(@NonNull Application application) {
        super(application);
        meetingsRoomDatabase = MeetingsRoomDatabase.getInstance(application.getApplicationContext());

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public LiveData<List<Section>> sectionLiveData() {
        return Transformations.map(meetingsRoomDatabase.meetingsDao().findAllMeetings(), inputMeeting -> {
            Log.d(TAG, "sectionLiveData: " + inputMeeting);

            List<Section> sectionList = new ArrayList<>();

            Map<LocalDate, List<Meeting>> map = inputMeeting.stream()
                    .collect(Collectors.groupingBy(meeting -> {
                        LocalDateTime dateTime = LocalDateTime.parse(meeting.getDate());
                        return dateTime.toLocalDate();
                    }, Collectors.toList()));

            Log.d(TAG, "sectionLiveData: " + map);
            map.forEach((date, meetings) -> {
                Log.d(TAG, "sectionLiveData: Map" + meetings);

                if (!date.isBefore(LocalDate.now())) {
                    if (date.equals(LocalDate.now())) {
                        sectionList.add(new Section("Today", date, meetings));
                    } else if (LocalDate.now().plusDays(1).equals(date)) {
                        sectionList.add(new Section("Tomorrow", date, meetings));
                    } else {
                        sectionList.add(new Section(date.toString(), date, meetings));
                    }
                }
            });

            sectionList.sort((o1, o2) -> o1.getDate().compareTo(o2.getDate()));
            Log.d(TAG, "sectionLiveData: S" + sectionList);
            return sectionList;
        });
    }

    public LiveData<List<Meeting>> getAllMeetings(){
        return meetingsRoomDatabase.meetingsDao().findAllMeetings();
    }

    public void deleteMeeting(Integer mMeetingUid) {
        MeetingsRoomDatabase.databaseWriteExecutor.submit(() -> {

            Meeting meeting = meetingsRoomDatabase
                    .meetingsDao()
                    .findMeetingById(mMeetingUid);

            meetingsRoomDatabase
                    .meetingsDao()
                    .deleteMeeting(meeting);

        });
    }
}
