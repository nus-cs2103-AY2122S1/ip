package duke.task;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EventTest {
    @Test
    public void testInvalidDate() {
        assertThrows(DukeException.class, () -> new Event("description", "2021-13-01"));
    }

    @Test
    public void testStringConversion() {
        assertEquals("[E][ ] desc (at: 01 Dec 2021)", new Event("desc", "2021-12-01").toString());
        assertEquals("[E][X] desc (at: 03 Oct 2021)", new Event("desc", "2021-10-03", true).toString());
    }

    @Test
    public void serialize() {
        assertEquals("E | 0 | desc | 2021-12-01", new Event("desc", "2021-12-01").serialize());
        assertEquals("E | 1 | desc | 2021-10-03", new Event("desc", "2021-10-03", true).serialize());
    }
}
