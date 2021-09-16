package taskMan.taskTypes;

import taskMan.exception.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


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
    public void todoToString_invalidInputHasDeadline_InvalidFormatException() {
        assertThrows(InvalidFormatException.class, () -> new Todo("todo2 /by", false)) ;
    }

    @Test
    public void todoToString_invalidInputHasEvent_InvalidFormatException() {
        assertThrows(InvalidFormatException.class, () -> new Todo("todo1 /at", false)) ;
    }


}