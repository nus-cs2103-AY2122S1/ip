package saber.tasklist;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import saber.task.Task;

public class TaskListTest {
    @Test
    public void addTest() {
        TaskList taskList = new TaskList(new ArrayList<>());
        Task item = new Task("item", false);
        taskList.add(item);
        assertEquals(1, taskList.size());
    }

    @Test
    public void getTest() {
        TaskList taskList = new TaskList(new ArrayList<>());
        Task item = new Task("item", false);
        taskList.add(item);
        Task task = taskList.get(0);
        assertEquals(item, task);
    }

    @Test
    public void sizeTest() {
        TaskList taskList = new TaskList(new ArrayList<>());
        Task item = new Task("item", false);
        Task item2 = new Task("item2", false);
        taskList.add(item);
        taskList.add(item2);
        assertEquals(2, taskList.size());
    }

    @Test
    public void deleteTest() {
        TaskList taskList = new TaskList(new ArrayList<>());
        Task item = new Task("item", false);
        taskList.add(item);
        taskList.delete(0);
        assertEquals(0, taskList.size());
    }
}
