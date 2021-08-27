package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.task.TaskList;


public class TaskListTest {
    private static ArrayList<String> simulatedStorageLoad;

    @Test void testLoadTodo() {
        simulatedStorageLoad = new ArrayList<>();
        simulatedStorageLoad.add("[D][ ] Assignment (by: Aug 23 2021 11:59 PM)");
        simulatedStorageLoad.add("[E][ ] Party (at: Feb 28 2021 03:55 PM)");
        simulatedStorageLoad.add("[T][x] Code");
        TaskList tasks = new TaskList(simulatedStorageLoad);
        assertEquals("[T][x] Code", tasks.getStringDes(3));
        assertEquals("[E][ ] Party (at: Feb 28 2021 03:55 PM)", tasks.getStringDes(2));
        assertEquals("[D][ ] Assignment (by: Aug 23 2021 11:59 PM)", tasks.getStringDes(1));
        assertEquals(3, tasks.size());
    }

    @Test void testLoadBlank() {
        simulatedStorageLoad = new ArrayList<>();
        TaskList tasks = new TaskList(simulatedStorageLoad);
        assertEquals(0, tasks.size());
    }


}
