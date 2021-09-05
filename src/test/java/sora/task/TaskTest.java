package sora.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

class TaskTest {
    @Test
    public void markAsDone_success() {
        Task task = new Todo("test task");
        task.markAsDone();

        String expected = "[T][X] test task";
        assertEquals(expected, task.toString());
    }

    @Test
    public void matchDescription_success() {
        Task task = new Event("test task", null, null, null);

        assertTrue(task.matchesKeyword("test"));
        assertTrue(task.matchesKeyword("task"));
        assertTrue(task.matchesKeyword("t"));

        assertFalse(task.matchesKeyword("apple"));
        assertFalse(task.matchesKeyword("banana"));
        assertFalse(task.matchesKeyword("tet"));

        assertFalse(task.matchesKeyword("todo"));
        assertFalse(task.matchesKeyword("deadline"));
        assertTrue(task.matchesKeyword("event"));
    }

    @Test
    public void compareTo_success() {
        Task todo1 = new Todo("");
        Task todo2 = new Todo("");

        LocalDateTime dateTime = LocalDateTime.of(2012, 5, 21, 3, 14);
        Task deadline = new Deadline("", dateTime);

        LocalDate date = LocalDate.of(2021, 1, 1);
        LocalTime startTime = LocalTime.of(4, 1);
        Task event = new Event("", date, startTime, null);

        assertEquals(0, todo1.compareTo(todo2));
        assertTrue(todo1.compareTo(deadline) > 0);
        assertTrue(event.compareTo(todo2) < 0);

        assertEquals(0, event.compareTo(event));
        assertTrue(deadline.compareTo(event) < 0);
    }
}
