package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void convertToTxtTest() {
        ToDo task = new ToDo("Test convertToTxt()");
        assertEquals("T | 0 | Test convertToTxt()", task.convertToText());
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
