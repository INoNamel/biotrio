package com.biotrio.app;

public class Seat {

    private int seat_num;
    private char row_char;
    private boolean occupation;

    Seat() {

    }

    public int getSeat_num() {
        return seat_num;
    }

    public void setSeat_num(int seat_num) {
        this.seat_num = seat_num;
    }

    public char getRow_char() {
        return row_char;
    }

    public void setRow_char(char row_char) {
        this.row_char = row_char;
    }

    public boolean getOccupation() {
        return occupation;
    }

    public void setOccupation(boolean occupation) {
        this.occupation = occupation;
    }

    public String seatId() {
        return Character.toString(row_char)+seat_num;
    }


}
