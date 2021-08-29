package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void toSavableFormat_todo_success() {
        Task task = new Todo("test");
        String expected = "T | 0 | test";
        assertEquals(expected, task.toSavableFormat());
    }

    @Test
    public void toSavableFormat_deadline_success() {
        Task task = new Deadline("test", LocalDate.parse("2020-10-10"));
        String expected = "D | 0 | test | 2020-10-10";
        assertEquals(expected, task.toSavableFormat());
    }

    @Test
    public void toSavableFormat_event_success() {
        Task task = new Event("test", "Aug 24th 2am-3am");
        String expected = "E | 0 | test | Aug 24th 2am-3am";
        assertEquals(expected, task.toSavableFormat());
    }
}
