package test;

import lebron.Lebron;
import lebron.exception.LebronException;
import lebron.task.Task;
import lebron.task.TaskList;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskListTest {
    private Lebron lebron = new Lebron();

    TaskListTest() {
    }

    @Test
    void add() {
        ArrayList<Task> lst = new ArrayList<Task>();
        lst.add(new TaskStub("swag"));
        TaskList taskList = new TaskList(lst);
        taskList.add(new TaskStub("yolo"));
        assertEquals(2, taskList.getSize());
    }

    @Test
    void markDone() {
        ArrayList<Task> lst = new ArrayList<Task>();
        lst.add(new TaskStub("swag"));
        TaskList taskList = new TaskList(lst);
        taskList.markDone(0);
        assertEquals("bob", taskList.getLst().get(0).getName());
    }

    @Test
    void delete() throws LebronException {
        ArrayList<Task> lst = new ArrayList<Task>();
        lst.add(new TaskStub("swag"));
        TaskList taskList = new TaskList(lst);
        taskList.delete(0);
        assertEquals(0, taskList.getSize());
    }
}
