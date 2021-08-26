package duke.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testDeadlineCreation() {
        assertEquals("[D][ ] return book (by: Aug 29 2021)", new Deadline("return book", "2021-08-29").toString());
    }

}

