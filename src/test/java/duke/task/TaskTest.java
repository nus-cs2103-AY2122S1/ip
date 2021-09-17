package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void testTaskSetDone() {
        Task task = new Task("taskTest");
        task.setDone();
        assertEquals("[X] taskTest", task.toString());
    }

}
