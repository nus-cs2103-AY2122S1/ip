package duke.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ParserTest {

    @Test
    void isValidTask_invalidTasks_failure() {
        assertFalse(Parser.isValidTask("deadline /at 1999-12-03 0000"));
        assertFalse(Parser.isValidTask("event /by 1999-12-03 0000"));
        assertFalse(Parser.isValidTask("deadline /by /at 1999-12-03 0000"));
        assertFalse(Parser.isValidTask("event /at /by 1999-12-03 0000"));
    }

    @Test
    void isMissingArg_tasksWithoutDesc_success() {
        assertTrue(Parser.isMissingArg("todo "));
        assertTrue(Parser.isMissingArg("todo  "));
        assertTrue(Parser.isMissingArg("todo           "));
        assertTrue(Parser.isMissingArg("todo                                     "));
    }
}