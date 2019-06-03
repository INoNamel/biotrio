package com.biotrio.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Model for MVC design pattern
 */
@Repository
class PerformanceRepository {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");


    @Autowired
    private JdbcTemplate jdbc;

    /**
     * @param display only available titles
     * @param like having a particular genre
     * @return list of all title objects from DB
     */
    List<Title> findAllTitles(boolean display, @Nullable String like) {
        try {
            String show = "";
            String search = "";

            if(display) {
                show = " WHERE display IS true ";
            }
            if(like != null) {
                search = " AND genre LIKE '%"+like+"%' ";
            }

            String query = ("SELECT * FROM title_list " + show + search +
                    " ORDER BY id DESC");
            SqlRowSet rs = jdbc.queryForRowSet(query);

            List<Title> titleList = new ArrayList<>();

            while (rs.next()) {
                Title title = new Title();
                title.setId(rs.getInt("id"));
                title.setDuration(rs.getInt("duration"));
                title.setTitle(rs.getString("title"));
                title.setActors(rs.getString("actors"));
                title.setProducer(rs.getString("producer"));
                title.setDescription(rs.getString("description"));
                title.setGenre(rs.getString("genre"));
                title.setEquipment(rs.getString("equipment"));
                title.setDisplay(rs.getBoolean("display"));
                titleList.add(title);
            }

            return titleList;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @param id title reference
     * @return title object from DB
     */
    Title findTitle(int id) {
        try {
            String query = ("SELECT * " +
                    "FROM title_list " +
                    "WHERE id = '"+id+"' ");
            SqlRowSet rs = jdbc.queryForRowSet(query);
            Title title = new Title();

            while (rs.next()) {
                title.setId(rs.getInt("id"));
                title.setDisplay(rs.getBoolean("display"));
                title.setTitle(rs.getString("title"));
                title.setGenre(rs.getString("genre"));
                title.setEquipment(rs.getString("equipment"));
                title.setDuration(rs.getInt("duration"));
                title.setActors(rs.getString("actors"));
                title.setProducer(rs.getString("producer"));
                title.setDescription(rs.getString("description"));
            }

            return title;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @param title form with new data to update a particular title in DB
     */
    void updateTitle(Title title) {
        jdbc.update("UPDATE title_list SET " +
                "title='" + title.getTitle() + "', " +
                "display=" + title.isDisplay() + ", " +
                "genre='" + title.getGenre() + "', " +
                "duration=" + title.getDuration() + ", " +
                "equipment= if('" + title.getEquipment() + "' = '', null, '" + title.getEquipment() + "'), " +
                "producer= if('" + title.getProducer() + "' = '', null, '" + title.getProducer() + "'), " +
                "actors= if('" + title.getActors() + "' = '', null, '" + title.getActors() + "'), " +
                "description= if('" + title.getDescription() + "' = '', null, '" + title.getDescription() + "') " +
                "WHERE id = " + title.getId()+ " ");
    }

    /**
     * @param id of a title to be deleted from DB
     */
    void deleteTitle(int id) {
        jdbc.update("DELETE FROM title_list WHERE id = " +id +" ");
    }

    /**
     * @param title form to create a new title in a DB
     */
    void addTitle(Title title) {
        jdbc.update(
            "INSERT INTO title_list " +
                    "(title, genre, duration, equipment, producer, actors, description) " +
                    "VALUES ('" + title.getTitle() + "', '" + title.getGenre() + "', " + title.getDuration() + ", " +
                    "if('" + title.getEquipment() + "' = '', null, '" + title.getEquipment() + "'), " +
                    "if('" + title.getProducer() + "' = '', null, '" + title.getProducer() + "'), " +
                    "if('" + title.getActors() + "' = '', null, '" + title.getActors() + "'), " +
                    "if('" + title.getDescription() + "' = '', null, '" + title.getDescription() + "'))");
    }

    /**
     * @return list of all performances from DB
     */
    List<Performance> findAllPerformances() {
        try {
            String query = ("SELECT theater_ref as theater_id, color, title_ref as title_id, title, performance.id as performance_id, date " +
                    "FROM performance " +
                    "INNER JOIN title_list ON performance.title_ref = title_list.id " +
                    "INNER JOIN theater ON performance.theater_ref = theater.id ORDER BY performance.id DESC ");
            SqlRowSet rs = jdbc.queryForRowSet(query);

            List<Performance> perfList = new ArrayList<>();
            while (rs.next()) {
                Performance performance = new Performance();
                Theater theater = new Theater();
                Title title = new Title();

                theater.setId(rs.getInt("theater_id"));
                theater.setColor(rs.getString("color"));

                title.setId(rs.getInt("title_id"));
                title.setTitle(rs.getString("title"));

                performance.setId(rs.getInt("performance_id"));
                performance.setTitle(title);
                performance.setTheater(theater);
                performance.setDate(LocalDateTime.parse(rs.getString("date"), formatter));
                perfList.add(performance);
            }

            return perfList;
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * @param title_id for performances having a particular title
     * @param perf_date to chose a particular day
     * @return list of all performances with a particular title
     */
    List<Performance> findAllPerformancesFor(String title_id, @Nullable String perf_date) {
        try {
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            String from, to;
            LocalDate date = LocalDate.now();

            if(perf_date != null && !perf_date.isEmpty()) {
                date = LocalDate.parse(perf_date, dateFormat);
            }
            if(LocalDate.now().isEqual(date)) {
                LocalDateTime datetime = LocalDateTime.now();
                from = datetime.format(dateTimeFormat);
                to = date.plusDays(1).format(dateFormat);
            } else {
                from = date.format(dateFormat);
                to = date.plusDays(1).format(dateFormat);
            }

            String query = ("SELECT id, date " +
                    "FROM performance " +
                    "WHERE title_ref = '"+title_id+"' AND date > '" +from+ "' AND date < '" + to + "' " +
                    "ORDER BY date");
            SqlRowSet rs = jdbc.queryForRowSet(query);

            List<Performance> perfList = new ArrayList<>();
            while (rs.next()) {
                Performance performance = new Performance();

                performance.setId(rs.getInt("id"));
                performance.setDate(LocalDateTime.parse(rs.getString("date"), formatter));
                perfList.add(performance);
            }

            return perfList;
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * @param id of the chosen performance
     * @return list of all dates up to 28 days from the moment of request of the chosen performance
     */
    List<String> findPerformanceDates(int id) {
        try {
        String query = ("SELECT COUNT(id) as amount, DATE_FORMAT(date, '%Y-%m-%d') as date " +
                "FROM performance " +
                "WHERE title_ref = " + id + " AND date BETWEEN NOW() AND DATE_ADD(NOW(), INTERVAL 28 DAY) " +
                "GROUP BY DAYOFMONTH(date) " +
                "ORDER BY date ");
        SqlRowSet rs = jdbc.queryForRowSet(query);

        List<String> dateList = new ArrayList<>();
        while (rs.next()) {
            dateList.add(rs.getString("date"));
        }

        return dateList;
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * @param performance form to create a new performance in a DB
     */
    void addPerformance(Performance performance) {
        jdbc.update(
                "INSERT INTO performance (id, title_ref, theater_ref, date) VALUES (?, ?, ?, ?)",
                performance.getId(), performance.getTitle().getId(), performance.getTheater().getId(), performance.getDate()
        );
    }

    /**
     * @param id of performance to be deleted from DB
     */
    void deletePerformance(int id) {
        jdbc.update("DELETE FROM performance WHERE id = " +id +" ");
    }

    /**
     * @param id of a chosen performance
     * @return performance object from DB
     */
    Performance findPerformance(int id) {
        try {
            String query = ("SELECT theater_ref as theater_id, color, title_ref as title_id, title, performance.id as performance_id, date, rows, seats " +
                    "FROM performance " +
                    "INNER JOIN title_list ON performance.title_ref = title_list.id " +
                    "INNER JOIN theater ON performance.theater_ref = theater.id " +
                    "WHERE performance.id = '"+id+"' ");
            SqlRowSet rs = jdbc.queryForRowSet(query);
            Performance performance = new Performance();
            while (rs.next()) {
                Theater theater = new Theater();
                Title title = new Title();

                theater.setId(rs.getInt("theater_id"));
                theater.setColor(rs.getString("color"));
                theater.setRows(rs.getInt("rows"));
                theater.setSeat(rs.getInt("seats"));

                theater.setSeats(new Seat[rs.getInt("rows")][rs.getInt("seats")]);

                title.setId(rs.getInt("title_id"));
                title.setTitle(rs.getString("title"));

                performance.setId(rs.getInt("performance_id"));
                performance.setTitle(title);
                performance.setTheater(theater);
                performance.setDate(LocalDateTime.parse(rs.getString("date"), formatter));
            }

            return performance;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @param performance form with new data to update a particular performance in DB
     */
    void updatePerformance(Performance performance) {
        jdbc.update("UPDATE performance SET " +
                "title_ref='" + performance.getTitle().getId() + "', " +
                "theater_ref='" + performance.getTheater().getId() + "', " +
                "date='" + performance.getDate() +"' " +
                "WHERE id = " + performance.getId()+ " ");
    }

    /**
     * @return list of all theater objects from DB
     */
    List<Theater> findAllTheaters() {
        try {
            String query = ("SELECT * FROM theater");
            SqlRowSet rs = jdbc.queryForRowSet(query);

            List<Theater> theaterList = new ArrayList<>();

            while (rs.next()) {
                Theater theater = new Theater();
                Seat[][] seats = new Seat[rs.getInt("rows")][rs.getInt("seats")];

                theater.setId(rs.getInt("id"));
                theater.setColor(rs.getString("color"));
                theater.setSeats(seats);
                theaterList.add(theater);
            }

            return theaterList;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @param id of the chosen theater
     * @return theater object with data from DB
     */
    Theater findTheater(int id) {
        try {
            String query = ("SELECT * FROM theater WHERE id = "+ id +" ");
            SqlRowSet rs = jdbc.queryForRowSet(query);
            Theater theater = new Theater();

            while (rs.next()) {
                Seat[][] seats = new Seat[rs.getInt("rows")][rs.getInt("seats")];

                theater.setId(rs.getInt("id"));
                theater.setColor(rs.getString("color"));
                theater.setRows(rs.getInt("rows"));
                theater.setSeat(rs.getInt("seats"));
                theater.setSeats(seats);
            }
            return theater;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @param id of theater to be deleted from DB
     */
    void deleteTheater(int id) {
        jdbc.update("DELETE FROM theater WHERE id = " +id +" ");
    }

    /**
     * @param theater form with new data to update a particular theater in DB
     */
    void updateTheater(Theater theater) {
        jdbc.update("UPDATE theater SET " +
            "color='" + theater.getColor() + "', " +
            "rows=" + theater.getRows() + ", " +
            "seats=" + theater.getSeat() + " " +
            "WHERE id = " + theater.getId()+ " ");
    }

    /**
     * @param theater form to create a new theater in a DB
     */
    void addTheater(Theater theater) {
        jdbc.update("INSERT INTO theater " +
            "(color, rows, seats) " +
            "VALUES ('" + theater.getColor() + "', " + theater.getRows() + ", " + theater.getSeat() + ") ");
    }

    /**
     * @param id of a chosen performance
     * @param search whether or not to search by phone number as well
     * @return list of bookings for particular performance
     */
    List<Booking> findBookingsFor(int id, @Nullable String search) {
        try {
            String tlf = "";
            if(search != null) {
                tlf = " AND phone = " + search;
            }

            String query = ("SELECT booking.id as booking_id, performance.id as performance_id, date, title, booked_on, phone, seat " +
                    "FROM booking " +
                    "INNER JOIN performance ON booking.performance_ref = performance.id " +
                    "INNER JOIN title_list on performance.title_ref = title_list.id " +
                    "WHERE booking.performance_ref = "+ id + tlf + " ");
            SqlRowSet rs = jdbc.queryForRowSet(query);
            List<Booking> bookings = new ArrayList<>();

            while (rs.next()) {
                Booking booking = new Booking();
                Performance performance = new Performance();
                Title title = new Title();
                title.setTitle(rs.getString("title"));
                performance.setId(rs.getInt("performance_id"));
                performance.setDate(LocalDateTime.parse(rs.getString("date"), formatter));
                performance.setTitle(title);

                booking.setId(rs.getInt("booking_id"));
                booking.setBooked_on(LocalDateTime.parse(rs.getString("booked_on"), formatter));
                booking.setPhone(rs.getString("phone"));
                booking.setSeat(rs.getString("seat"));

                booking.setPerformance(performance);
                bookings.add(booking);
            }

            return bookings;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @param id of the booking to be deleted from DB
     */
    void deleteBooking(int id)  {
        jdbc.update("DELETE FROM booking WHERE id = "+id+" ");
    }

    /**
     * @param performance to occupy with seats from the DB
     * @return performance populated with occupied and non-occupied seats
     */
    Performance occupySeats(Performance performance) {
        List<String> occupations = reservationList(performance.getId());
        if(performance.getId() > 0) {
            for (int y = 0; y < performance.getTheater().getSeats().length; y++) {
                for (int i = 0; i < performance.getTheater().getSeats()[0].length; i++) {
                    performance.getTheater().getSeats()[y][i] = new Seat();

                    performance.getTheater().getSeats()[y][i].setRow_char((char) (65+y));
                    performance.getTheater().getSeats()[y][i].setSeat_num(i+1);

                    if(occupations.contains(((char) (65+y))+""+(i+1))) {
                        performance.getTheater().getSeats()[y][i].setOccupation(true);
                    } else {
                        performance.getTheater().getSeats()[y][i].setOccupation(false);
                    }
                }
            }
        }
        return performance;
    }

    /**
     * @param id of the chosen performance
     * @return list with all reserved seat ids for selected performance
     */
    private List<String> reservationList(int id) {

        String query = ("SELECT seat " +
                "FROM booking " +
                "INNER JOIN performance ON performance.id = booking.performance_ref " +
                "WHERE performance_ref = "+id+" ");
        SqlRowSet rs = jdbc.queryForRowSet(query);

        List<String> arr = new ArrayList<>();

        while (rs.next()) {
            arr.add(rs.getString("seat"));
        }

        return arr;
    }

    /**
     * @param booking form with input from the customer
     * @return success or failure message based on input
     */
    List<String> validateReservation(Booking booking) {
        boolean continueBooking = true;
        List<String> msg = new ArrayList<>();

        if(booking.getSeat().isEmpty()) {
            msg.add("No seats chosen");
            continueBooking = false;
        }
        if(booking.getPerformance().getId() <= 0) {
            msg.add("No performance chosen");
            continueBooking = false;
        }
        if(booking.getPhone().isEmpty() || !booking.getPhone().matches("(\\d{6,9})")) {
            msg.add("Phone number required");
            continueBooking = false;
        }
        if(booking.getPerformance().getDate().isBefore(LocalDateTime.now().plusMinutes(30))){
            msg.add("Sorry, the show is about to start");
            continueBooking = false;
        }
        if(booking.getPerformance().getDate().minusDays(28).isAfter(LocalDateTime.now())){
            msg.add("Booking is max 28 days before show");
            continueBooking = false;
        }
        if(continueBooking) {
            List<Seat> getSeats = Arrays.stream(booking.getPerformance().getTheater().getSeats()).flatMap(Arrays::stream).collect(Collectors.toList());

            List<String> all_seats = new ArrayList<>();
            List<String> booked = Arrays.asList(booking.getSeat().split(","));

            for (Seat seat: getSeats) {
                all_seats.add(seat.seatId());
            }

            if(!all_seats.containsAll(booked)) {
                continueBooking = false;
                msg.add("Invalid seat ID");
            }
        }

        if(continueBooking) {
            try {
                bookSeats(booking);
                msg.add("Successful reservation");
                msg.add("seats: "+booking.getSeat());
                msg.add("tlf.: "+booking.getPhone());
            } catch (DataIntegrityViolationException e){
                msg.add("Seats are occupied!");
            }
        }
        return msg;
    }

    /**
     * @param booking contains selected seats as well as performance id
     * @throws DataIntegrityViolationException in case of customer bypassing client-side validation
     */
    private void bookSeats(Booking booking) throws DataIntegrityViolationException {
        String[] seats = booking.getSeat().split(",");
        for (String seat : seats) {
            jdbc.update(
                    "INSERT INTO booking (performance_ref, seat, phone) VALUES (?, ?, ?)",
                    booking.getId(), seat, booking.getPhone()
            );
        }
    }

    /**
     * @param pass provided password
     * @return true or false whether the provided password matches one stored in DB
     */
    boolean verifyPass(String pass) {
        String query = ("SELECT drowssap FROM terces WHERE nimda = 1");
        SqlRowSet rs = jdbc.queryForRowSet(query);
        String q = "";
        while (rs.next()) {
            q = rs.getString("drowssap");
        }
        return q.equals(pass);
    }
}
