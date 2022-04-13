import duke.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskTest {

    @Test
    public void testGetStatusIcon() {
        Task t = new Task("");
        assertEquals(" ", t.getStatusIcon());
        t.markAsDone();
        assertEquals("X", t.getStatusIcon());
    }

    @Test
    public void testToString() {
        Task t = new Task("abc");
        assertEquals("[ ] abc", t.toString());
        t.markAsDone();
        assertEquals("[X] abc", t.toString());
    }
}