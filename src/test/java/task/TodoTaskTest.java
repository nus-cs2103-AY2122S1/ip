package task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTaskTest {

    private TodoTask testTask = new TodoTask("Test");

    @Test
    public void testGetTaskState() {
        assertEquals(testTask.getTaskState(), "[T][ ] Test");
    }

    @Test
    public void testConvertToStorageFormat() {
        assertEquals(testTask.convertToStorageFormat(), "T,0,Test");
    }
}
