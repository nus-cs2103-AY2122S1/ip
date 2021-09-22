package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    void testStringConversion() {
        Deadline deadline = null;
        try {
            deadline = new Deadline("return book", "02/02/2022 1800");
        } catch (DukeException e) {
            //there should not be an exception here.
        }
        assertEquals("[D][ ] return book (by: 02 Feb 2022 06:00PM)", deadline.toString());
    }
}
