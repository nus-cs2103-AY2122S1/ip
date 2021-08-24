package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testToData() {
        String expected = "D | 0 | gym | Aug 25 2021, 8 AM";
        Deadline deadline = new Deadline("gym", "25/8/2021 0800");
        assertEquals(expected, deadline.toData());
    }
}