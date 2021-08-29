package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import duke.util.DukeDate;

public class EventTest {
    @Test
    public void testToString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm");
        LocalDateTime dateStartTime = LocalDateTime.parse("12-Dec-2020 12:00", format);
        LocalDateTime dateEndTime = LocalDateTime.parse("12-Jan-2020 20:00", format);
        Event tempTask = new Event("testing the tester", new DukeDate(dateStartTime,
            dateEndTime));
        assertEquals("[E][ ] testing the tester (at: 12-Dec-2020 12:00 to 12-Jan-2020 20:00)",
            tempTask.toString());
    }

    @Test
    public void testGetDeadline() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm");
        LocalDateTime dateStartTime = LocalDateTime.parse("12-Dec-2020 12:00", format);
        LocalDateTime dateEndTime = LocalDateTime.parse("12-Jan-2020 20:00", format);
        DukeDate savedDuration = new DukeDate(dateStartTime, dateEndTime);
        Event tempTask = new Event("testing the tester", savedDuration);
        assertEquals(tempTask.getDuration(), savedDuration);
    }

    @Test
    public void testCompleteTask() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm");
        LocalDateTime dateStartTime = LocalDateTime.parse("12-Dec-2020 12:00", format);
        LocalDateTime dateEndTime = LocalDateTime.parse("12-Jan-2020 20:00", format);
        DukeDate savedDuration = new DukeDate(dateStartTime, dateEndTime);
        Event tempTask = new Event("testing the tester", savedDuration);
        tempTask.completeTask();
        assertEquals(tempTask.isCompleted(), true);
    }

    @Test
    public void testGetDescription() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm");
        LocalDateTime dateStartTime = LocalDateTime.parse("12-Dec-2020 12:00", format);
        LocalDateTime dateEndTime = LocalDateTime.parse("12-Jan-2020 20:00", format);
        DukeDate savedDuration = new DukeDate(dateStartTime, dateEndTime);
        Event tempTask = new Event("testing the tester", savedDuration);
        assertEquals(tempTask.getDescription(), "testing the tester");
    }

    @Test
    public void testCheckTerm() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm");
        LocalDateTime dateStartTime = LocalDateTime.parse("12-Dec-2020 12:00", format);
        LocalDateTime dateEndTime = LocalDateTime.parse("12-Jan-2020 20:00", format);
        DukeDate savedDuration = new DukeDate(dateStartTime, dateEndTime);
        Event tempTask = new Event("testing the tester", savedDuration);
        assertEquals(tempTask.checkTerm("tester"), true);
    }
}
