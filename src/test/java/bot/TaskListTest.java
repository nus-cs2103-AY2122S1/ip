package bot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import task.Task;
import task.TodoTask;

public class TaskListTest {

    private ArrayList<Task> arrayList;
    private TaskList taskList;

    /**
     * Tests the getSize() method.
     */
    @Test
    public void testGetSize() {
        arrayList.add(new TodoTask("Test1"));
        arrayList.add(new TodoTask("Test2"));
        arrayList.add(new TodoTask("Test3"));

        taskList.addTask(new TodoTask("Test1"));
        taskList.addTask(new TodoTask("Test2"));
        taskList.addTask(new TodoTask("Test3"));

        assertEquals(arrayList.size(), taskList.getSize());
    }

}
