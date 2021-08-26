package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void testMarkAsDone() {
        Task task = new Task("", false);
        task.markAsDone();
        assertEquals("[X] ", task.toString());
    }
}
