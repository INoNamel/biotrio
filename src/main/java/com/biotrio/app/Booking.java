package com.biotrio.app;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Booking {
    private int id;
    private Performance performance;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime booked_on;
    private String phone, seat;

    private DateTimeFormatter fullDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    Booking() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Performance getPerformance() {
        return performance;
    }

    public void setPerformance(Performance performance) {
        this.performance = performance;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public LocalDateTime getBooked_on() {
        return booked_on;
    }

    public void setBooked_on(LocalDateTime booked_on) {
        this.booked_on = booked_on;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String fullDateTime() {
        return this.booked_on.format(fullDateTime);
    }
}
