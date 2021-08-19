package task;

import org.junit.jupiter.api.Test;
import util.DateTimeUtils;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    void getAtDateTime() {
        LocalDate atDate = DateTimeUtils.parseDate("2021-08-01");
        LocalTime startTime = DateTimeUtils.parseTime("13:00");
        LocalTime endTime = DateTimeUtils.parseTime("15:00");
        EventDateTime eventDateTime = new EventDateTime(atDate, startTime, endTime);
        Event event = new Event("Project meeting", eventDateTime);
        EventDateTime expected = new EventDateTime(atDate, startTime, endTime);
        assertEquals(expected.getAtDate(), event.getAtDateTime().getAtDate());
        assertEquals(expected.getStartTime(), event.getAtDateTime().getStartTime());
        assertEquals(expected.getEndTime(), event.getAtDateTime().getEndTime());
    }

    @Test
    void formatTask() {
        LocalDate atDate = DateTimeUtils.parseDate("2021-08-01");
        LocalTime startTime = DateTimeUtils.parseTime("13:00");
        LocalTime endTime = DateTimeUtils.parseTime("15:00");
        EventDateTime eventDateTime = new EventDateTime(atDate, startTime, endTime);
        Event event = new Event("Project meeting", eventDateTime);
        String[] expected = new String[]{"E", "1", "Project meeting", "2021-08-01 13:00-15:00"};
        assertArrayEquals(expected, event.formatTask());
    }

    @Test
    void markAsDone() {
        LocalDate atDate = DateTimeUtils.parseDate("2021-08-01");
        LocalTime startTime = DateTimeUtils.parseTime("13:00");
        LocalTime endTime = DateTimeUtils.parseTime("15:00");
        EventDateTime eventDateTime = new EventDateTime(atDate, startTime, endTime);
        Event event = new Event("Project meeting", eventDateTime);
        Event doneEvent = event.markAsDone();
        Event expected = new Event("Project meeting", eventDateTime, true);
        assertEquals(expected.toString(), doneEvent.toString());
    }

    @Test
    void testToString() {
        LocalDate atDate = DateTimeUtils.parseDate("2021-08-01");
        LocalTime startTime = DateTimeUtils.parseTime("13:00");
        LocalTime endTime = DateTimeUtils.parseTime("15:00");
        EventDateTime eventDateTime = new EventDateTime(atDate, startTime, endTime);
        Event event = new Event("Project meeting", eventDateTime);
        String expected = "[E][ ] Project meeting (at: Aug 01 2021 01:00 pm-03:00 pm)";
        assertEquals(expected, event.toString());
    }
}
