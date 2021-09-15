package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void testNewDeadline() {
        assertEquals("[D][ ] return book (by: Aug 29 2021)", new Deadline("return book", "2021-08-29").toString());
    }

}

