package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void convertToTxtTest() {
        Deadline task = new Deadline("Test convertToTxt()", "2021-08-22 1000");
        assertEquals("D | 0 | Test convertToTxt() | 2021-08-22 1000", task.convertToText());
    }

    @Test
    public void toStringTest_date() {
        Deadline task = new Deadline("Test toString() with Date", "2021-08-22");
        assertEquals("[D][ ] Test toString() with Date (by: Aug 22 2021)", task.toString());
    }

    @Test
    public void toStringTest_dateTime() {
        Deadline task = new Deadline("Test toString() with DateTime", "2021-08-22 1000");
        assertEquals("[D][ ] Test toString() with DateTime (by: Aug 22 2021 10.00 AM)", task.toString());
    }

    @Test
    public void toStringTest_normal() {
        Deadline task = new Deadline("Test toString()", "Tonight");
        assertEquals("[D][ ] Test toString() (by: Tonight)", task.toString());
    }

    @Test
    public void markAsDoneTest() {
        Deadline task = new Deadline("Test markAsDone()", "2021-08-22 1000");
        task.markAsDone();
        assertEquals("[D][X] Test markAsDone() (by: Aug 22 2021 10.00 AM)", task.toString());
    }
}
