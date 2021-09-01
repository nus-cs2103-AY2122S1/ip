package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    /**
     * Test the testToData method
     */
    @Test
    public void testToData() {
        String expected = "D | 0 | gym | Aug 25 2021, 8 am";
        Deadline deadline = new Deadline("gym", "25/8/2021 0800");
        assertEquals(expected, deadline.toData());
    }
}
