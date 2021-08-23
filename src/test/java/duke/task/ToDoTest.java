package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void testToString(){
        ToDo tempTask = new ToDo("testing the tester");
        assertEquals("[T][ ] testing the tester", tempTask.toString());
    }

    @Test
    public void testCompleteTask() {
        ToDo tempTask = new ToDo("testing the tester");
        tempTask.completeTask();
        assertEquals(tempTask.isCompleted(), true);
    }

    @Test
    public void testGetDescription() {
        ToDo tempTask = new ToDo("testing the tester");
        assertEquals(tempTask.getDescription(), "testing the tester");
    }
}
