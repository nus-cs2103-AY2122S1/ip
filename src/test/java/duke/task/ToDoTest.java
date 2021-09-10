package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void testToString() {
        ToDo task = new ToDo("read book");
        assertEquals("[T][✗] read book", task.toString());
    }

    @Test
    public void testSaveAsString() {
        ToDo task = new ToDo(CompletionStatus.COMPLETED, "read book");
        assertEquals("T | 1 | read book", task.convertToString());
    }

    @Test
    public void testGetStatusIcon() {
        ToDo task = new ToDo(CompletionStatus.COMPLETED, "read book");
        assertEquals("✓", task.getStatusIcon());
    }

    @Test
    public void testGetDate() {
        ToDo task = new ToDo("read book");
        assertEquals(null, task.getDate());
    }

    @Test
    public void testGetTime() {
        ToDo task = new ToDo("read book");
        assertEquals(null, task.getTime());
    }

    @Test
    public void testCheckDueBeforeDate() {
        ToDo task = new ToDo("read book");
        LocalDate date = LocalDate.now();
        assertEquals(false, task.checkDueBeforeDate(date));
    }

    @Test
    public void testMarkAsDone() {
        ToDo task = new ToDo(CompletionStatus.COMPLETED, "read book");
        task.markTaskAsDone();
        assertEquals("[T][✓] read book", task.toString());
    }

    @Test
    public void testOnDate() {
        ToDo task = new ToDo("read book");
        assertEquals(false, task.isOnDate("2021-08-23"));
    }
}
