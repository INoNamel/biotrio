package com.biotrio.app;

import org.springframework.lang.Nullable;

import javax.validation.constraints.Max;

public class Title {

    private int id;

    private boolean display;

    @Max(999)
    private int duration;

    private String title, genre, equipment, producer, actors, description;

    Title() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(@Nullable String equipment) {
        this.equipment = equipment;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(@Nullable String producer) {
        this.producer = producer;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(@Nullable String actors) {
        this.actors = actors;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(@Nullable String description) {
        this.description = description;
    }

    public boolean isDisplay() {
        return display;
    }

    public void setDisplay(boolean display) {
        this.display = display;
    }
}
