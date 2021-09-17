package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Class to test TaskList objects.
 */
public class TaskListTest {

    /**
     * Checks if tasks are added and retrieved properly.
     */
    @Test
    public void addTaskTest() {
        TaskList taskList = new TaskList();
        for (int i = 0; i < 10; i++) {
            taskList.addTask(new Todo("Hello!"));
        }
        assertEquals(11, taskList.getTaskStrings().length);
    }

    /**
     * Checks if tasks are deleted properly.
     */
    @Test
    public void deleteTaskTest() {
        TaskList taskList = new TaskList();
        for (int i = 0; i < 10; i++) {
            taskList.addTask(new Todo("Yo!"));
        }
        try {
            for (int i = 0; i < 5; i++) {
                taskList.deleteTask(4);
            }
            assertEquals(6, taskList.getTaskStrings().length);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Checks if tasks are being found correctly.
     */
    @Test
    public void findMatchingTasksTest() {
        TaskList taskList = new TaskList();
        for (int i = 0; i < 5; i++) {
            taskList.addTask(new Todo("Hello!"));
        }
        for (int i = 0; i < 6; i++) {
            taskList.addTask(new Todo("No!"));
        }
        assertEquals(7, taskList.findMatchingTasks("No").length);
    }
}
