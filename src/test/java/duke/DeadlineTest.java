package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import duke.task.Deadline;

/**
 * JUnit test class for {@code Deadline}.
 */
public class DeadlineTest {
    /**
     * Tests the inheritance of {@code Deadline} from its superclass {@code Task}.
     */
    @Test
    public void testInheritance() {
        Deadline testDeadline = new Deadline("Ice latte.", LocalDateTime.now());
        assertEquals("Ice latte.", testDeadline.getTaskName());
        assertFalse(testDeadline.isDone());
        testDeadline.toggleDone();
        assertTrue(testDeadline.isDone());
        assertFalse(new Deadline("Need to get pizza!!!", LocalDateTime.now()).isDone());
    }

    /**
     * Tests the overridden {@code toString()} method.
     */
    @Test
    public void testToString() {
        assertEquals("[D][ ] Deadline! (by: Dec 31 2021, 12:00 PM)",
                new Deadline("Deadline!", LocalDateTime.of(2021, 12, 31, 12, 0)).toString());
        assertEquals("[D][ ] fud (by: Aug 20 1990, 5:00 PM)",
                new Deadline("fud", LocalDateTime.of(1990, 8, 20, 17, 0)).toString());
    }
}
