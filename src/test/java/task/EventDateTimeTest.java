package task;

import org.junit.jupiter.api.Test;
import util.DateTimeUtils;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventDateTimeTest {

    @Test
    void getAtDate() {
        LocalDate atDate = DateTimeUtils.parseDate("2021-08-01");
        LocalTime startTime = DateTimeUtils.parseTime("13:00");
        LocalTime endTime = DateTimeUtils.parseTime("15:00");
        EventDateTime eventDateTime = new EventDateTime(atDate, startTime, endTime);
        LocalDate expected = DateTimeUtils.parseDate("2021-08-01");
        assertEquals(expected, eventDateTime.getAtDate());
    }

    @Test
    void getStartTime() {
        LocalDate atDate = DateTimeUtils.parseDate("2021-08-01");
        LocalTime startTime = DateTimeUtils.parseTime("13:00");
        LocalTime endTime = DateTimeUtils.parseTime("15:00");
        EventDateTime eventDateTime = new EventDateTime(atDate, startTime, endTime);
        LocalTime expected = DateTimeUtils.parseTime("13:00");
        assertEquals(expected, eventDateTime.getStartTime());
    }

    @Test
    void getEndTime() {
        LocalDate atDate = DateTimeUtils.parseDate("2021-08-01");
        LocalTime startTime = DateTimeUtils.parseTime("13:00");
        LocalTime endTime = DateTimeUtils.parseTime("15:00");
        EventDateTime eventDateTime = new EventDateTime(atDate, startTime, endTime);
        LocalTime expected = DateTimeUtils.parseTime("15:00");
        assertEquals(expected, eventDateTime.getEndTime());
    }
}
