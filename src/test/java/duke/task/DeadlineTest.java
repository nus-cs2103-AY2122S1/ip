package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import duke.util.DukeDate;

public class DeadlineTest {
    @Test
    public void testToString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse("12-Dec-2020 12:00", format);
        Deadline tempTask = new Deadline("testing the tester", new DukeDate(dateTime));
        assertEquals("[D][ ] testing the tester (by: 12-Dec-2020 12:00)", tempTask.toString());
    }

    @Test
    public void testGetDeadline() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse("12-Dec-2020 12:00", format);
        DukeDate savedDate = new DukeDate(dateTime);
        Deadline tempTask = new Deadline("testing the tester", savedDate);
        assertEquals(tempTask.getDeadline(), savedDate);
    }

    @Test
    public void testCompleteTask() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse("12-Dec-2020 12:00", format);
        Deadline tempTask = new Deadline("testing the tester", new DukeDate(dateTime));
        tempTask.completeTask();
        assertEquals(tempTask.isCompleted(), true);
    }

    @Test
    public void testGetDescription() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse("12-Dec-2020 12:00", format);
        Deadline tempTask = new Deadline("testing the tester", new DukeDate(dateTime));
        assertEquals(tempTask.getDescription(), "testing the tester");
    }

    @Test
    public void testCheckTerm() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse("12-Dec-2020 12:00", format);
        Deadline tempTask = new Deadline("testing the tester", new DukeDate(dateTime));
        assertEquals(tempTask.checkTerm("testing"), true);
    }

}
