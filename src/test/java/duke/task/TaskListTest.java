package duke.task;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void emptyListHasZeroSize() {
        TaskList taskList = new TaskList();
        assertEquals(0, taskList.size());
    }

    @Test
    public void getReturnsCorrectElement() {
        List<DukeTask> dukeTaskList = new ArrayList<>();
        dukeTaskList.add(new DukeSimpleTask("task 1"));
        dukeTaskList.add(new DukeSimpleTask("task 2"));
        dukeTaskList.add(new DukeSimpleTask("task 3"));
        dukeTaskList.add(new DukeSimpleTask("task 4"));
        TaskList taskList = new TaskList(dukeTaskList);
        for (int i = 0; i < dukeTaskList.size(); i++) {
            assertEquals(dukeTaskList.get(i), taskList.getTaskAt(i));
        }
    }
}
