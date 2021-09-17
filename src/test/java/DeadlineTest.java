import duke.task.Deadline;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    static final String DESCRIPTION = "I am a deadline task";
    static final String DATE = "2019-10-15";

    @Test
    public void testStringConversion_done() {
        Deadline task = new Deadline(DESCRIPTION, DATE, true);
        String expectedOutput = "[D][X] " + DESCRIPTION + " (by: " + DATE + ")";
        assertEquals(expectedOutput, task.toString());
    }

    @Test
    public void testStringConversion_undone() {
        Deadline task = new Deadline(DESCRIPTION, DATE, false);
        String expectedOutput = "[D][] " + DESCRIPTION + " (by: " + DATE + ")";
        assertEquals(expectedOutput, task.toString());
    }

    @Test
    public void testStringWriteConversion() {
        Deadline task = new Deadline(DESCRIPTION, DATE, true);
        String expectedOutput = "deadline | 1 | I am a deadline task | 2019-10-15";
        assertEquals(expectedOutput, task.toWriteString());
    }
}
