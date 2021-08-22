package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void convertToTxtTest() {
        ToDo task = new ToDo("Test convertToTxt()");
        assertEquals("T | 0 | Test convertToTxt()", task.convertToTxt());
    }

    @Test
    public void toStringTest() {
        ToDo task = new ToDo("Test toString()");
        assertEquals("[T][ ] Test toString()", task.toString());
    }

    @Test
    public void markAsDoneTest() {
        ToDo task = new ToDo("Test markAsDone()");
        task.markAsDone();
        assertEquals("[T][X] Test markAsDone()", task.toString());
    }
}
