package taskman.tasktypes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import taskman.exception.DukeException;
import taskman.exception.EmptyDescriptionException;
import taskman.exception.EmptyTimeException;
import taskman.exception.InvalidDateException;
import taskman.exception.InvalidFormatException;
import taskman.exception.InvalidTimeException;


/**
 * Deadline test class
 */
class EventTest {

    @Test
    public void eventToString_validStringInput_stringReturned() throws DukeException {
        assertEquals(
                "[E][ ] event1 (at: Sep 9 2019 1900 hrs)",
                new Event("event1 /at 2019-09-09 1900", false).toString());
    }

    @Test
    public void deadlineSaveTask_validStringInput_stringReturned() throws DukeException {
        assertEquals(
                "E F event1 /at 2019-09-09 1900",
                new Event("event1 /at 2019-09-09 1900", false).saveTaskTxt());
    }

    @Test
    public void deadlineSaveTaskCsv_validStringInput_stringReturned() throws DukeException {
        assertEquals(
                "E,F,event1,/at,2019-09-09,1900",
                new Event("event1 /at 2019-09-09 1900", false).saveTaskCsv());
    }

    @Test
    public void deadlineStringInput_invalidDateInput_invalidDateExceptionThrown() {
        assertThrows(InvalidDateException.class, () -> new Event("event1 /at 20-20-2019 1900", false));
    }

    @Test
    public void deadlineStringInput_invalidTimeInput_invalidTimeExceptionThrown() {
        assertThrows(InvalidTimeException.class, () -> new Event("event1 /at 2019-09-09 2500", false));
    }

    @Test
    public void deadlineStringInput_missingTimeInput_emptyDescriptionExceptionThrown() {
        assertThrows(EmptyDescriptionException.class, () -> new Event(" /at 2019-09-09 1900", false));
    }

    @Test
    public void deadlineStringInput_missingTimeInput_emptyTimeExceptionThrown() {
        assertThrows(EmptyTimeException.class, () -> new Event("event1 /at 2019-09-09", false));
    }

    @Test
    public void deadlineStringInput_missingTimeInput_invalidFormatExceptionThrown() {
        assertThrows(InvalidFormatException.class, () -> new Event("/at", false));
    }

}
