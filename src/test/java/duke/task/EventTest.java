package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void convertToTxtTest() {
        Event task = new Event("Test convertToTxt()", "2021-08-22 1000");
        assertEquals("E | 0 | Test convertToTxt() | 2021-08-22 1000", task.convertToText());
    }

    @Test
    public void toStringTest_date() {
        Event task = new Event("Test toString() with Date", "2021-08-22");
        assertEquals("[E][ ] Test toString() with Date (at: Aug 22 2021)", task.toString());
    }

    @Test
    public void toStringTest_dateTime() {
        Event task = new Event("Test toString() with DateTime", "2021-08-22 1000");
        assertEquals("[E][ ] Test toString() with DateTime (at: Aug 22 2021 10.00 AM)", task.toString());
    }

    @Test
    public void toStringTest_normal() {
        Event task = new Event("Test toString()", "Tonight");
        assertEquals("[E][ ] Test toString() (at: Tonight)", task.toString());
    }

    @Test
    public void markAsDoneTest() {
        Event task = new Event("Test markAsDone()", "2021-08-22 1000");
        task.markAsDone();
        assertEquals("[E][X] Test markAsDone() (at: Aug 22 2021 10.00 AM)", task.toString());
    }
}
