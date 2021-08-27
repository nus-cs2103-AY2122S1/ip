package duke.task;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeadlineTest {
    @Test
    public void testInvalidDate() {
        assertThrows(DukeException.class, () -> new Deadline("description", "2021-13-01"));
    }

    @Test
    public void testStringConversion() {
        assertEquals("[D][ ] desc (by: 01 Dec 2021)", new Deadline("desc", "2021-12-01").toString());
        assertEquals("[D][X] desc (by: 03 Oct 2021)", new Deadline("desc", "2021-10-03", true).toString());
    }

    @Test
    public void serialize() {
        assertEquals("D | 0 | desc | 2021-12-01", new Deadline("desc", "2021-12-01").serialize());
        assertEquals("D | 1 | desc | 2021-10-03", new Deadline("desc", "2021-10-03", true).serialize());
    }
}
