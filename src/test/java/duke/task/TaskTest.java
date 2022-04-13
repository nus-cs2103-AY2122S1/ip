package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void testMarkAsDone() {
        Task task = new Task("", false);
        task.markAsDone();
        assertEquals("[X] ", task.toString());
    }
}
