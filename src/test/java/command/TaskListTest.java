package command;

import duke.command.TaskList;
import duke.exception.DuplicateException;
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
        try {
            testingList = new TaskList();
            assertEquals(0, testingList.size());
            testingList.addTask(task1);
            testingList.addTask(task2);
            assertEquals(2, testingList.size());
            testingList.addTask(task3);
            assertEquals(3, testingList.size());
        } catch (DuplicateException e) {
            System.out.println("There are duplicate elements want to be added in the list");
        }
    }

    @Test
    public void getTest() {
        testingList = new TaskList();
        try {
            testingList.addTask(task1);
            testingList.addTask(task2);
            assertEquals(task1, testingList.getTask(0));
            assertEquals(task2, testingList.getTask(1));
        } catch (DuplicateException e) {
            System.out.println("There are duplicate elements want to be added in the list");
        }
    }

    @Test
    public void removeTest() {
        try {
            testingList = new TaskList();
            testingList.addTask(task1);
            testingList.addTask(task2);
            assertEquals(task2, testingList.removeTask(1));
            assertEquals(task1, testingList.removeTask(0));
        } catch (DuplicateException e) {
            System.out.println("There are duplicate elements want to be added in the list");
        }
    }
}
