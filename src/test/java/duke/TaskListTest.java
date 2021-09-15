package duke;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;
import duke.tasks.taskType;
import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void addTaskTest() {
        ArrayList<Task> tasks = new ArrayList<>(
                Arrays.asList(
                        new ToDo("sleep"),
                        new Event("dinner", "7PM")
                ));
        TaskList list = new TaskList(tasks);
        String[] details = {"eat"};
        list.addTask(taskType.TODO, details);
        assertEquals(new ToDo("eat").toString(), list.getTask(3).toString());
    }

    @Test
    public void deleteTaskTest() {
        ArrayList<Task> tasks = new ArrayList<>(
                Arrays.asList(
                        new ToDo("sleep"),
                        new Event("dinner", "7PM")
                ));
        TaskList list = new TaskList(tasks);
        try {
            list.deleteTask(1);
        } catch (DukeException e) {
            fail();
        }
        assertEquals(1, tasks.size());
    }

    @Test
    public void deleteTaskTest_invalidTaskNo() {
        ArrayList<Task> tasks = new ArrayList<>(
                Arrays.asList(
                        new ToDo("sleep"),
                        new Event("dinner", "7PM")
                ));
        TaskList list = new TaskList(tasks);
        assertThrows(DukeException.class, ()-> {
            list.deleteTask(3);
        });
    }

    @Test
    public void doneTaskTest() {
        ArrayList<Task> tasks = new ArrayList<>(
                Arrays.asList(
                        new ToDo("sleep"),
                        new Event("dinner", "7PM")
                ));
        TaskList list = new TaskList(tasks);
        try {
            list.doneTask(1);
        } catch (DukeException e) {
            fail();
        }
        assertEquals("[T][X] sleep", tasks.get(0).toString());
    }

    @Test
    public void findTaskTest() {
        ArrayList<Task> tasks = new ArrayList<>(
                Arrays.asList(
                        new ToDo("sleep"),
                        new Event("dinner", "7PM")
                ));
        TaskList list = new TaskList(tasks);
        ArrayList<Task> result = new ArrayList<>(
                Arrays.asList(
                        new ToDo("sleep")
                ));
        ArrayList<Task> match = list.find("sleep");
        assertEquals(result.toString(), match.toString());
    }


}
