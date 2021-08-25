package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void testDeadlineConstructor() {
        Deadline dl = new Deadline("essay submission", "2021-08-30");
        String expected = "[D][ ] essay submission (by: Aug 30 2021)";
        assertEquals(expected, dl.toString());
    }

}
