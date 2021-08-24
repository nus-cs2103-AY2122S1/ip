package duke;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DeadlineTest {

    @Test
    public void toStringTest() {
        try {
            Deadline deadline = new Deadline("Description", "18/12/2002");
            assertEquals("[D][ ] Description (by: 18/12/2002)", deadline.toString());
        } catch (DukeException e) {

        }
    }
}
