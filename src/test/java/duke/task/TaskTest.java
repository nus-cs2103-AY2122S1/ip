package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.DukeException;


public class TaskTest {
    @Test
    public void getTaskTypeTest() {
        try {
            assertEquals(Task.TaskName.getTaskType("[T]"), Task.TaskName.TODO);
            assertEquals(Task.TaskName.getTaskType("todo"), Task.TaskName.TODO);
            assertEquals(Task.TaskName.getTaskType("[D]"), Task.TaskName.DEADLINE);
            assertEquals(Task.TaskName.getTaskType("deadline"), Task.TaskName.DEADLINE);
            assertEquals(Task.TaskName.getTaskType("[E]"), Task.TaskName.EVENT);
            assertEquals(Task.TaskName.getTaskType("event"), Task.TaskName.EVENT);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
