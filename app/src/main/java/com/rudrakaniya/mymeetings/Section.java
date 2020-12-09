package com.rudrakaniya.mymeetings;

import com.rudrakaniya.mymeetings.entity.Meeting;

import java.time.LocalDate;
import java.util.List;

public class Section {

    private String sectionName;
    private LocalDate date;
    private List<Meeting> sectionItems;

    public Section(String sectionName, LocalDate date, List<Meeting> sectionItems) {
        this.sectionName = sectionName;
        this.date = date;
        this.sectionItems = sectionItems;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public List<Meeting> getSectionItems() {
        return sectionItems;
    }

    public void setSectionItems(List<Meeting> sectionItems) {
        this.sectionItems = sectionItems;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Section{" +
                "sectionName='" + sectionName + '\'' +
                ", date=" + date +
                ", sectionItems=" + sectionItems +
                '}';
    }
}
