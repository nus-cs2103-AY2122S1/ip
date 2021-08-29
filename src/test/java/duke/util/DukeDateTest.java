package duke.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class DukeDateTest {
    @Test
    public void testToString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm");
        LocalDateTime dateStartTime = LocalDateTime.parse("12-Dec-2020 12:00", format);
        LocalDateTime dateEndTime = LocalDateTime.parse("12-Jan-2020 20:00", format);
        DukeDate tempDate = new DukeDate(dateStartTime, dateEndTime);
        assertEquals("12-Dec-2020 12:00 to 12-Jan-2020 20:00", tempDate.toString());
    }

    @Test
    public void testGetStartTime() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm");
        LocalDateTime dateStartTime = LocalDateTime.parse("12-Dec-2020 12:00", format);
        LocalDateTime dateEndTime = LocalDateTime.parse("12-Jan-2020 20:00", format);
        DukeDate savedDuration = new DukeDate(dateStartTime, dateEndTime);
        assertEquals(dateStartTime, savedDuration.getStartTime());
    }

    @Test
    public void testGetEndTime() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm");
        LocalDateTime dateStartTime = LocalDateTime.parse("12-Dec-2020 12:00", format);
        LocalDateTime dateEndTime = LocalDateTime.parse("12-Jan-2020 20:00", format);
        DukeDate savedDuration = new DukeDate(dateStartTime, dateEndTime);
        assertEquals(dateEndTime, savedDuration.getEndTime());
    }

    @Test
    public void testGetEndTimeString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm");
        LocalDateTime dateStartTime = LocalDateTime.parse("12-Dec-2020 12:00", format);
        LocalDateTime dateEndTime = LocalDateTime.parse("12-Jan-2020 20:00", format);
        DukeDate savedDuration = new DukeDate(dateStartTime, dateEndTime);
        assertEquals(savedDuration.getEndTimeString(), "12-Jan-2020 20:00");
    }
}
