package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.DukeException;


public class TaskTest {
    @Test
    public void getTaskTypeTest() {
        try {
            assertEquals(TaskName.getTaskType("[T]"), TaskName.TODO);
            assertEquals(TaskName.getTaskType("todo"), TaskName.TODO);
            assertEquals(TaskName.getTaskType("[D]"), TaskName.DEADLINE);
            assertEquals(TaskName.getTaskType("deadline"), TaskName.DEADLINE);
            assertEquals(TaskName.getTaskType("[E]"), TaskName.EVENT);
            assertEquals(TaskName.getTaskType("event"), TaskName.EVENT);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
