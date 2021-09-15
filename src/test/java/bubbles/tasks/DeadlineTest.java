package bubbles.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import bubbles.exceptions.EmptyTaskException;
import bubbles.exceptions.InvalidFormatException;

public class DeadlineTest {
    // as we are printing out the output, we can test what we sent to System.out.println() for printing
    @Test
    public void testDeadlineFormat() throws EmptyTaskException, InvalidFormatException {
        Deadline d = Deadline.addDeadline("buy groceries /by 2021-08-30", false);
        assertEquals("D | 0 | buy groceries | by 2021-08-30", d.format());
    }

    @Test
    public void testDeadlineToString() throws EmptyTaskException, InvalidFormatException {
        Deadline d = Deadline.addDeadline("complete cs2103t tutorial /by 2021-08-27", true);
        assertEquals("[D] [X] complete cs2103t tutorial (by: 27 Aug 2021)", d.toString());
    }
}
