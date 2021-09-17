package luke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void deadlineToStringTest() {
        try {
            Deadline deadline = new Deadline("testing-deadline", "2021-08-20");
            assertEquals("[D][ ] testing-deadline(by: Aug 20 2021)", deadline.toString());
        } catch (LukeException e) {
            assertEquals(new LukeException("Invalid timing format! Enter dates in yyyy-mm-dd format").getMessage(), e.getMessage());
        }
    }

    @Test
    public void deadlineSaveStringTest() {
        try {
            Deadline deadline = new Deadline("testing-deadline", "2021-08-20");
            assertEquals("D,0,testing-deadline,2021-08-20", deadline.saveString());
        } catch (LukeException e) {
            assertEquals(new LukeException("Invalid timing format! Enter dates in yyyy-mm-dd format").getMessage(), e.getMessage());
        }
    }

    @Test
    public void invalidDateTest() {
        try {
            Deadline deadline = new Deadline("testing-deadline", "20th Aug");
        } catch (LukeException e) {
            assertEquals(new LukeException("Invalid timing format! Enter dates in yyyy-mm-dd format").getMessage(), e.getMessage());
        }
    }
}