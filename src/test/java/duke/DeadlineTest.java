package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void testInstanceCreationWithTwoParameter() {
        String expected = "homework (by: Dec 31 2020 11:59PM)";
        Deadline deadline = new Deadline("homework", "2020-12-31 2359");
        assertEquals(expected, deadline.getDescription());
    }

    @Test
    public void testInstanceCreationWithThreeParameter() {
        String expected = "homework (by: Dec 31 2020 11:59PM)";
        Deadline deadline = new Deadline("homework", "Dec 31 2020", "11:59PM");
        assertEquals(expected, deadline.getDescription());
    }
}
