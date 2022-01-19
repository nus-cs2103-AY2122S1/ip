package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

public class DeadlineTest {
    @Test
    public void testInvalidDate() {
        assertThrows(DukeException.class, () -> new Deadline("description", "2021-13-01"));
    }

    @Test
    public void testNullPriority() {
        assertThrows(DukeException.class, () -> new Deadline("description", null, "2021-12-01", false));
    }

    @Test
    public void testStringConversion() {
        assertEquals("[D][ ] [MEDIUM] desc (by: 01 Dec 2021)", new Deadline("desc", "2021-12-01").toString());
        assertEquals("[D][X] [MEDIUM] desc (by: 03 Oct 2021)", new Deadline("desc", "2021-10-03", true).toString());
        assertEquals("[D][X] [HIGH] desc (by: 03 Oct 2021)",
                new Deadline("desc", Priority.HIGH, "2021-10-03", true).toString());
    }

    @Test
    public void serialize() {
        assertEquals("D | MEDIUM | 0 | desc | 2021-12-01", new Deadline("desc", "2021-12-01").serialize());
        assertEquals("D | MEDIUM | 1 | desc | 2021-10-03", new Deadline("desc", "2021-10-03", true).serialize());
        assertEquals("D | HIGH | 1 | desc | 2021-10-03",
                new Deadline("desc", Priority.HIGH, "2021-10-03", true).serialize());
    }
}
