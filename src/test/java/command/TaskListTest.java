package command;

import duke.TaskList;
import duke.task.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    private static TaskList testingList = new TaskList();
    private static Task task1 = new Task("task1");
    private static Task task2 = new Task("task2");
    private static Task task3 = new Task("task3");

    @Test
    public void sizeTest() {
        testingList = new TaskList();
        assertEquals(0, testingList.size());
        testingList.add(task1);
        testingList.add(task2);
        assertEquals(2, testingList.size());
        testingList.add(task3);
        assertEquals(3, testingList.size());
    }

    @Test
    public void getTest() {
        testingList = new TaskList();
        testingList.add(task1);
        testingList.add(task2);
        assertEquals(task1, testingList.get(0));
        assertEquals(task2, testingList.get(1));
    }

    @Test
    public void removeTest() {
        testingList = new TaskList();
        testingList.add(task1);
        testingList.add(task2);
        assertEquals(task2, testingList.remove(1));
        assertEquals(task1, testingList.remove(0));
    }
}
