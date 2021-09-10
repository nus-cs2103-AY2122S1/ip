package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

public class EventTest {
    @Test
    public void testInvalidDate() {
        assertThrows(DukeException.class, () -> new Event("description", "2021-13-01"));
    }

    @Test
    public void testNullPriority() {
        assertThrows(DukeException.class, () -> new Event("description", null, "2021-12-01", true));
    }

    @Test
    public void testStringConversion() {
        assertEquals("[E][ ] [MEDIUM] desc (at: 01 Dec 2021)", new Event("desc", "2021-12-01").toString());
        assertEquals("[E][X] [MEDIUM] desc (at: 03 Oct 2021)", new Event("desc", "2021-10-03", true).toString());
        assertEquals("[E][X] [HIGH] desc (at: 03 Oct 2021)",
                new Event("desc", Priority.HIGH, "2021-10-03", true).toString());
    }

    @Test
    public void serialize() {
        assertEquals("E | MEDIUM | 0 | desc | 2021-12-01", new Event("desc", "2021-12-01").serialize());
        assertEquals("E | MEDIUM | 1 | desc | 2021-10-03", new Event("desc", "2021-10-03", true).serialize());
        assertEquals("E | HIGH | 1 | desc | 2021-10-03",
                new Event("desc", Priority.HIGH, "2021-10-03", true).serialize());
    }
}
