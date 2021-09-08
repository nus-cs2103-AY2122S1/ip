package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import duke.task.Deadline;

public class DeadlineTest {
    @Test
    public void testEncoding() {
        Deadline deadline = new Deadline("ceremony", "tmr");
        assertEquals(deadline.encoding(), "D&&InProgress&&ceremony&&tmr");
        deadline.setDone();
        assertEquals(deadline.encoding(), "D&&Done&&ceremony&&tmr");
    }

    @Test
    public void testToString() {
        Deadline deadline = new Deadline("ceremony", "tmr");
        assertEquals(deadline.toString(), "[D][ ] ceremony (by: tmr)");
        deadline.setDone();
        assertEquals(deadline.toString(), "[D][X] ceremony (by: tmr)");
    }

    @Test
    public void testGetDate() {
        Deadline deadline1 = new Deadline("ceremony", "tmr");
        Deadline deadline2 = new Deadline("ceremony", "2021-02-23");
        assertEquals(deadline1.getDate(), null);
        assertEquals(deadline2.getDate(), LocalDate.parse("2021-02-23"));
    }

}
