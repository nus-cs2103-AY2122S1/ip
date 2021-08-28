package java.task;

import main.java.task.TodoTask;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTaskTest {

    TodoTask testTask = new TodoTask("Test");

    @Test
    public void testGetTaskState() {
        assertEquals(testTask.getTaskState(), "[T][ ] Test");
    }

    @Test
    public void testConvertToStorageFormat() {
        assertEquals(testTask.convertToStorageFormat(), "T,0,Test");
    }
}