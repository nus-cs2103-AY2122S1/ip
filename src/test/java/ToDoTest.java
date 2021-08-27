import duke.task.ToDo;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

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