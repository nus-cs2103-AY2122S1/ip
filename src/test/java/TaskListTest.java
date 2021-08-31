import lebron.task.Task;
import lebron.task.TaskList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.*;
import lebron.Lebron;

class TaskListTest {
    Lebron lebron = new Lebron();

    TaskListTest() throws IOException {
    }

    @Test
    void add() {
        ArrayList<Task> lst = new ArrayList<Task>();
        lst.add(new TaskStub("swag"));
        TaskList taskList = new TaskList(lst, lebron);
        taskList.add(new TaskStub("yolo"));
        assertEquals(2, taskList.getSize());
    }

    @Test
    void markDone() {
        ArrayList<Task> lst = new ArrayList<Task>();
        lst.add(new TaskStub("swag"));
        TaskList taskList = new TaskList(lst, lebron);
        taskList.markDone(0);
        assertEquals("bob", taskList.getLst().get(0).getName());
    }

    @Test
    void delete() {
        ArrayList<Task> lst = new ArrayList<Task>();
        lst.add(new TaskStub("swag"));
        TaskList taskList = new TaskList(lst, lebron);
        taskList.delete(0);
        assertEquals(0, taskList.getSize());
    }
}