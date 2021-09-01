import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.ToDo;

public class ToDoTest {
    @Test
    public void testToString() {
        ToDo testTask = new ToDo("testToDo");
        assertEquals("[T][ ] testToDo", testTask.toString());
    }

    @Test
    public void testToSaveFormat() {
        ToDo testTask = new ToDo("testToDo");
        testTask.markAsDone();
        assertEquals("T|1|testToDo", testTask.convertToSaveFormat());
    }
}
