package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void test() {
        Deadline deadline1 = new Deadline("Homework", "2020-01-09");
        Deadline deadline2 = new Deadline("Assignment", "2021-11-21");

        assertEquals("D | | Homework | Jan 9 2020", deadline1.toString());
        assertEquals("D | | Assignment | Nov 21 2021", deadline2.toString());

    }
}
