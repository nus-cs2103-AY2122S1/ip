package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTest {

    @Test
    public void testStringConversion() {
        assertEquals("[D][ ] submission (by: Oct 2 2020)",
                new Deadline(submission, 2020-10-02).toString());
    }
}
