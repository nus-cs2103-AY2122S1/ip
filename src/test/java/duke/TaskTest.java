package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TaskTest {
    Task t = new Task("eat dinner");

    @Test
    public void testGetDescription() {
        assertEquals("eat dinner", t.getDescription());
    }


}
