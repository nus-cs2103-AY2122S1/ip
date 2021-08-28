package duke.data.task;

import duke.data.TaskHandler;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TaskHandlerTest {
    @Test
    public void formatTasksToSaveTest_success() {
        ArrayList<Task> list = new ArrayList<>();
        list.add(new ToDo("read book"));
        list.add(new Deadline("do assignment", "21/09/21 1230"));
        list.add(new Event("birthday party", "22/09/21 1730"));
        TaskHandler th = new TaskHandler(list);
        assertEquals("[T][ ] read book\n" +
                "[D][ ] do assignment (by: Sep 21 12:30 PM)\n" +
                "[E][ ] birthday party (at: Sep 22 5:30 PM)", th.formatTaskToSave());

    }
}
