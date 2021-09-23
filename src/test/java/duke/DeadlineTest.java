package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTest {

    @Test
    void testStringConversion() {
        Deadline deadline = null;
        try {
            deadline = new Deadline("return book", "02/02/2022 1800");
        } catch (DukeException e) {
            fail();
        }
        assertEquals("[D][ ] return book (by: 02 Feb 2022 06:00PM)", deadline.toString());
    }
}
