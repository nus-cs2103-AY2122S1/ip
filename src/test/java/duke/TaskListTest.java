package duke;

import duke.processor.TaskList;
import duke.task.Deadline;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void find_true() {
        TaskList test = new TaskList();
        String out = "";
        test.addTask(new Deadline("finish CS2103T code", "time"));
        out = test.find("code");
        assertEquals("Here are the tasks in your list with keyword code:\n    " +
                "1.[D][ ] finish CS2103T code (by: time)\n", out);
    }
}