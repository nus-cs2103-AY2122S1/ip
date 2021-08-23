package duke.task;

import duke.util.DukeDate;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testToString(){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse("12-Dec-2020 12:00", format);
        Deadline tempTask = new Deadline("testing the tester", new DukeDate(dateTime));
        assertEquals("[D][ ] testing the tester (by: 12-Dec-2020 12:00)", tempTask.toString());
    }

    @Test
    public void testGetDeadline(){
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
}
