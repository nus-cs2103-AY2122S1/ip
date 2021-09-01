package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void test() {
        Deadline deadline = new Deadline("Homework", "2020-01-09");
        assertEquals("D | | Homework | Jan 9 2020", deadline.toString());
    }
}
