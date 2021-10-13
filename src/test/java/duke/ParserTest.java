package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * This class encapsulates a unit test for the Parser class.
 *
 * @author Kleon Ang
 */
public class ParserTest {
    /**
     * Tests the parse method when a list command is given with an empty TaskList.
     */
    @Test
    public void parse_list_exceptionThrown() {
        TaskList taskList = new TaskList();
        Parser parser = new Parser(taskList);
        assertThrows(DukeException.class, () -> parser.parse("list"));
    }

    /**
     * Tests the parse method when a new Deadline is added.
     *
     * @throws DukeException A Duke-specific exception that may occur when adding a new Deadline.
     */
    @Test
    public void parse_newDeadline_taskAdded() throws DukeException {
        TaskList taskList = new TaskList();
        Parser parser = new Parser(taskList);
        String expected = "Got it. I've added this task:\n  "
                + "[D][MEDIUM][ ] lab 1\n  (by: Sep 4 2021, 2:00 pm)"
                + "\nNow you have 1 tasks in the list.";
        assertEquals(expected, parser.parse("deadline lab 1 /by 2021-09-04 14:00"));
    }
}
