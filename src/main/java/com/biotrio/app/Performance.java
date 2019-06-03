package com.biotrio.app;

import org.springframework.lang.Nullable;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

class Performance {
    private DateTimeFormatter fullDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private int id;
    private Title title;
    private Theater theater;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime date;
    private DateTimeFormatter day = DateTimeFormatter.ofPattern("MMMM d EEEE"), time = DateTimeFormatter.ofPattern("HH:mm");

    Performance(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(@Nullable Title title) {
        this.title = title;
    }

    public Theater getTheater() {
        return theater;
    }

    public void setTheater(@Nullable Theater theater) {
        this.theater = theater;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String fullDate() {
        return this.date.format(fullDate);
    }

    public String getDay() {
        return this.date.format(day);
    }

    public String getTime() {
        return this.date.format(time);
    }

    public String getWeekDay() {
        return this.date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.US);
    }
}
