package sun.data.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import sun.data.TaskHandler;

public class TaskHandlerTest {
    @Test
    public void formatTasksToSaveTest_success() {
        ArrayList<Task> storageList = new ArrayList<>();
        ArrayList<Task> archiveList = new ArrayList<>();
        storageList.add(new ToDo("read book"));
        storageList.add(new Deadline("do assignment", "21/09/21 1230"));
        storageList.add(new Event("birthday party", "22/09/21 1730"));
        TaskHandler th = new TaskHandler(storageList, archiveList);
        assertEquals("[T][ ] read book\n"
               + "[D][ ] do assignment (by: 21/09/21 1230)\n"
                   + "[E][ ] birthday party (at: 22/09/21 1730)", th.formatTasksToSave("storage"));

    }
}
