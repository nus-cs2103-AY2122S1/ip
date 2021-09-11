package duke.taskTypes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import duke.exception.DukeException;
import duke.exception.EmptyTimeException;
import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidFormatException;
import duke.exception.InvalidDateException;
import duke.exception.InvalidTimeException;


import duke.taskTypes.Deadline;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


/**
 * Deadline test class
 */
class DeadlineTest {

    @Test
    public void deadlineToString_validStringInput_stringReturned() throws DukeException {
        Assertions.assertEquals(
                "[D][ ] dead1 (by: Sep 9 2019 1900 hrs)",
                new Deadline("dead1 /by 2019-09-09 1900", false).toString());
    }

    @Test
    public void deadlineSaveTask_validStringInput_stringReturned() throws DukeException {
        assertEquals(
                "D F dead1 /by 2019-09-09 1900",
                new Deadline("dead1 /by 2019-09-09 1900", false).saveTask());
    }

    @Test
    public void deadlineSaveTaskCsv_validStringInput_stringReturned() throws DukeException {
        assertEquals(
                "D,F,dead1,/by,2019-09-09,1900",
                new Deadline("dead1 /by 2019-09-09 1900", false).saveTaskCsv());
    }

    @Test
    public void deadlineStringInput_invalidDateInput_invalidDateExceptionThrown() {
        assertThrows(InvalidDateException.class, () -> new Deadline("dead1 /by 20-20-2019 1900", false)) ;
    }

    @Test
    public void deadlineStringInput_invalidTimeInput_invalidTimeExceptionThrown() {
        assertThrows(InvalidTimeException.class, () -> new Deadline("dead1 /by 2019-09-09 2500", false)) ;
    }

    @Test
    public void deadlineStringInput_missingTimeInput_emptyDescriptionExceptionThrown() {
        assertThrows(EmptyDescriptionException.class, () -> new Deadline(" /by 2019-09-09 1900", false)) ;
    }

    @Test
    public void deadlineStringInput_missingTimeInput_emptyTimeExceptionThrown() {
        assertThrows(EmptyTimeException.class, () -> new Deadline("dead1 /by 2019-09-09", false)) ;
    }

    @Test
    public void deadlineStringInput_missingTimeInput_invalidFormatExceptionThrown() {
        assertThrows(InvalidFormatException.class, () -> new Deadline("/by", false)) ;
    }
}