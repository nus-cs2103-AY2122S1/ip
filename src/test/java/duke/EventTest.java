package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import duke.task.Event;

/**
 * JUnit test class for {@code Event}.
 */
public class EventTest {
    /**
     * Tests the inheritance of {@code Event} from its superclass {@code Task}.
     */
    @Test
    public void testInheritance() {
        Event testEvent = new Event("Ice latte.", LocalDateTime.now());
        assertEquals("Ice latte.", testEvent.getTaskName());
        assertFalse(testEvent.isDone());
        testEvent.toggleDone();
        assertTrue(testEvent.isDone());
        assertFalse(new Event("Need to get pizza!!!", LocalDateTime.now()).isDone());
    }

    /**
     * Tests the overridden {@code toString()} method.
     */
    @Test
    public void testToString() {
        assertEquals("[E][ ] Event! (at: Dec 31 2021, 12:00 PM)",
                new Event("Event!", LocalDateTime.of(2021, 12, 31, 12, 0)).toString());
        assertEquals("[E][ ] coffeee (at: Aug 20 1990, 5:00 PM)",
                new Event("coffeee", LocalDateTime.of(1990, 8, 20, 17, 0)).toString());
    }
}
