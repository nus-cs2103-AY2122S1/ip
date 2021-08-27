package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testToString() {
        Event task = new Event("project meeting","23/08/2021 2000 2200");
        assertEquals("[E][ ] project meeting (by: Aug 23 2021 20:00-22:00)", task.toString());
    }

    @Test
    public void testSaveAsString() {
        Event task = new Event("1", "project meeting","23/08/2021 2000 2200");
        assertEquals("E | 1 | project meeting | 2021-08-23 20:00 22:00", task.saveAsString());
    }

    @Test
    public void testGetStatusIcon() {
        Event task = new Event("1", "project meeting","23/08/2021 2000 2200");
        assertEquals("X", task.getStatusIcon());
    }

    @Test
    public void testMarkAsDone() {
        Event task = new Event("project meeting","23/08/2021 2000 2200");
        task.markTaskAsDone();
        assertEquals("[E][X] project meeting (by: Aug 23 2021 20:00-22:00)", task.toString());
    }

    @Test
    public void testOnDate_sameDate() {
        Event task = new Event("project meeting","23/08/2021 2000 2200");
        assertEquals(true, task.isOnDate("2021-08-23"));
    }

    @Test
    public void testOnDate_differentDate() {
        Event task = new Event("project meeting","23/08/2021 2000 2200");
        assertEquals(false, task.isOnDate("2021-08-21"));
    }
}
