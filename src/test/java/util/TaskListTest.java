package util;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import side.util.TaskList;

public class TaskListTest {
    @Test
    public void lengthTest() {
        TaskList t = new TaskList();
        assertEquals(t.length(), 0);
    }

    @Test
    public void addTaskTest() {
        TaskList t = new TaskList();
        assertEquals(t.length(), 0);
        t.addTask("Test");
        assertEquals(t.length(), 1);
    }

    @Test
    public void addDeadlineTest() {
        TaskList t = new TaskList();
        assertEquals(t.length(), 0);
        t.addDeadline("Test", "2020-11-11");
        assertEquals(t.length(), 1);
    }

    @Test
    public void addEventTest() {
        TaskList t = new TaskList();
        assertEquals(t.length(), 0);
        t.addEvent("Test", "2020-11-11", "2020-11-12");
        assertEquals(t.length(), 1);
    }

    @Test
    public void markTaskDoneTest() {
        TaskList t = new TaskList();
        t.addTask("Test");
        assertEquals(t.markTaskDone(0), "Fine, I'll mark it for you: [T][x]Test ");
    }

    @Test
    public void deleteTaskTest() {
        TaskList t = new TaskList();
        t.addTask("Test");
        assertEquals(t.delete(0), "Fine, I'll delete: [T][ ]Test \n"
                + "You now have 0 tasks...");
    }

    @Test
    public void toStringTest() {
        TaskList t1 = new TaskList();
        assertEquals(t1.toString(), "No tasks yet, stop checking...");
        TaskList t2 = new TaskList();
        t2.addTask("Test");
        assertEquals(t2.toString(), "Fine, here are your tasks: \n"
                + "1. [T][ ]Test \n");
    }
}
