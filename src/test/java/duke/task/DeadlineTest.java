package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    /**
     * Tests Deadline constructor.
     */
    @Test
    public void testDeadlineConstructor() {
        Deadline dl = new Deadline("essay submission", "2021-08-30");
        String expected = "[D][ ] essay submission (by: Aug 30 2021)";
        assertEquals(expected, dl.toString());
    }

}
