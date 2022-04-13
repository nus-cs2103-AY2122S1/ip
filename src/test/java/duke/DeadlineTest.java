package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void toStringTest() {
        try {
            Deadline deadline = new Deadline("Description", "18/12/2002");
            assertEquals("[D][ ] Description (by: 18/12/2002)", deadline.toString());
        } catch (DukeException e) {
            System.out.println(e.toString());
        }
    }
}
