import duke.task.Event;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testToString() {
        Event testTask = new Event("testEvent", "test time");
        assertEquals("[E][ ] testEvent (at: test time)", testTask.toString());
    }

    @Test
    public void testToSaveFormat() {
        Event testTask = new Event("testEvent", "test time");
        testTask.markAsDone();
        assertEquals("E|1|testEvent|test time", testTask.convertToSaveFormat());
    }
}
