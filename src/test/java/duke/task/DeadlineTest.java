package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

class DeadlineTest {

    @Test
    public void testToString() throws DukeException {
        assertEquals("[D][ ] sleep(by: Dec 12 2019)", new Deadline("sleep", "2019-12-12").toString());
        assertEquals("[D][ ] eat(by: Jan 12 2022)", new Deadline("eat", "2022-01-12").toString());
    }

    @Test
    public void testToFileString() throws DukeException {
        assertEquals("D |   | sleep | 2019-12-12", new Deadline("sleep", "2019-12-12").toFileString());
        assertEquals("D |   | eat | 2022-01-12", new Deadline("eat", "2022-01-12").toFileString());
        assertEquals("D |   | walk | 2030-02-12", new Deadline("walk", "2030-02-12").toFileString());
    }

    @Test
    public void deadline_wrongFormat_exceptionThrown() {
        try {
            assertEquals(0, new Deadline("sleep", "2011/12/12"));
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("Please enter date in this format: YYYY-MM-DD", e.getMessage());
        }
    }
}
