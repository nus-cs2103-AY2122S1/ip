package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    void deadline_normalInput_writtenCorrectly() {
        assertEquals("D | 0 | Test Deadline | 25-06-2021 12:00",
                new Deadline("Test Deadline", "25-06-2021 12:00").databaseString());
    }

    @Test
    public void event_normalInput_writtenCorrectly() {
        assertEquals("E | 0 | Test Event | 25-06-2021 12:00",
                new Event("Test Event", "25-06-2021 12:00").databaseString());
    }

    @Test
    public void todo_normalInput_writtenCorrectly() {
        assertEquals("T | 0 | Test Todo",
                new Todo("Test Todo").databaseString());
    }
}
