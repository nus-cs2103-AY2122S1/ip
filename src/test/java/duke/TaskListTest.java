package duke;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import task.Task;

public class TaskListTest {

    @Test
    public void addTest(){
        TaskList test = new TaskList();
        test.add(Task.createTask("todo", "Test Task Name"));
        Assertions.assertEquals("0: [T][ ] Test Task Name\n", test.toString());
    }

    @Test
    public void doneTest(){
        TaskList test = new TaskList();
        test.add(Task.createTask("todo", "Test Task Name"));
        test.doneTask(0);
        Assertions.assertEquals("0: [T][X] Test Task Name\n", test.toString());
    }

    @Test
    public void deleteTest(){
        TaskList test = new TaskList();
        test.add(Task.createTask("todo", "Test Task Name"));
        Assertions.assertEquals("0: [T][ ] Test Task Name\n", test.toString());
        test.deleteTask(0);
        Assertions.assertEquals("", test.toString());
    }

    @Test
    public void saveTaskListTest(){
        TaskList test = new TaskList();
        test.add(Task.createTask("todo", "Test Task Name"));
        test.add(Task.createTask("event", "Test Event /at 2021-01-01"));
        String stored = test.saveTasklist();
        Assertions.assertEquals("todo|Test Task Name|0\n" +
                "event|Test Event/at2021-01-01|0\n", stored);
    }
}
