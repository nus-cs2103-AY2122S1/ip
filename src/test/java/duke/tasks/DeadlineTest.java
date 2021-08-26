package duke.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void toString_mockDeadline_success() {
        Deadline deadline = new Deadline("return book", "2021-10-12");
        assertEquals("[D][ ] return book (by: Oct 12 2021)", deadline.toString());
    }
}
