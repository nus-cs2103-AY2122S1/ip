package taskman.tasktypes;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import taskman.exception.DukeException;
import taskman.exception.InvalidFormatException;

/**
 * Deadline test class
 */
class TodoTest {

    @Test
    public void todoToString_validStringInput_stringReturned() throws DukeException {
        Assertions.assertEquals(
                "[T][ ] todo1",
                new Todo("todo1", false).toString());
    }

    @Test
    public void todoToString_invalidInputHasDeadline_invalidFormatException() {
        assertThrows(InvalidFormatException.class, () -> new Todo("todo2 /by", false));
    }

    @Test
    public void todoToString_invalidInputHasEvent_invalidFormatException() {
        assertThrows(InvalidFormatException.class, () -> new Todo("todo1 /at", false));
    }

}
