package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void testToString() {
        Event task = new Event("project meeting", "23/08/2021 2000 2200");
        assertEquals("[E][✗] project meeting (by: Aug 23 2021 20:00-22:00)", task.toString());
    }

    @Test
    public void testSaveAsString() {
        Event task = new Event(CompletionStatus.COMPLETED, "project meeting",
            "23/08/2021 2000 2200");
        assertEquals("E | 1 | project meeting | 2021-08-23 20:00 22:00", task.convertToString());
    }

    @Test
    public void testGetStatusIcon() {
        Event task = new Event(CompletionStatus.COMPLETED, "project meeting",
            "23/08/2021 2000 2200");
        assertEquals("✓", task.getStatusIcon());
    }

    @Test
    public void testGetDate() {
        LocalDate date = LocalDate.of(2021, 9, 4);
        Event task = new Event("project meeting", "04/09/2021 2000 2200");
        assertEquals(date, task.getDate());
    }

    @Test
    public void testGetTime() {
        LocalTime time = LocalTime.of(20, 0);
        Event task = new Event("project meeting", "04/09/2021 2000 2200");
        assertEquals(time, task.getTime());
    }

    @Test
    public void testMarkAsDone() {
        Event task = new Event("project meeting", "23/08/2021 2000 2200");
        task.markTaskAsDone();
        assertEquals("[E][✓] project meeting (by: Aug 23 2021 20:00-22:00)", task.toString());
    }

    @Test
    public void testOnDate_sameDate() {
        Event task = new Event("project meeting", "23/08/2021 2000 2200");
        assertEquals(true, task.isOnDate("2021-08-23"));
    }

    @Test
    public void testOnDate_differentDate() {
        Event task = new Event("project meeting", "23/08/2021 2000 2200");
        assertEquals(false, task.isOnDate("2021-08-21"));
    }
}
