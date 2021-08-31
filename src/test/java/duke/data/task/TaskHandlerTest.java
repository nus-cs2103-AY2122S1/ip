package duke.data.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.data.TaskHandler;

public class TaskHandlerTest {
    @Test
    public void formatTasksToSaveTest_success() {
        ArrayList<Task> list = new ArrayList<>();
        list.add(new ToDo("read book"));
        list.add(new Deadline("do assignment", "21/09/21 1230"));
        list.add(new Event("birthday party", "22/09/21 1730"));
        TaskHandler th = new TaskHandler(list);
        assertEquals("[T][ ] read book\n"
               + "[D][ ] do assignment (by: 21/09/21 1230)\n"
                   + "[E][ ] birthday party (at: 22/09/21 1730)", th.formatTasksToSave());

    }
}
