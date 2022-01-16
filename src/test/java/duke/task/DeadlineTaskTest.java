package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTaskTest {

    @Test
    public void stringRepresentationTest() {
        Task task = new DeadlineTask("test name", null);
        assertEquals(task.toString(), "[D][ ] test name (by: )");
        task.markCompleted();
        assertEquals(task.toString(), "[D][X] test name (by: )");
    }

    @Test
    public void taskTypeTest() {
        Task task = new DeadlineTask("test type", null);
        assertEquals(task.getType(), TaskType.DEADLINE);
    }

}
