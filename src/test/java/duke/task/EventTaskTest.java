package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTaskTest {

    @Test
    public void stringRepresentationTest() {
        Task task = new EventTask("test name", null);
        assertEquals(task.toString(), "[E][ ] test name (at: )");
        task.markCompleted();
        assertEquals(task.toString(), "[E][X] test name (at: )");
    }

    @Test
    public void taskTypeTest() {
        Task task = new EventTask("test type", null);
        assertEquals(task.getType(), TaskType.EVENT);
    }

}
