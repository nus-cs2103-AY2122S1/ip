package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

/**
 * This class encapsulates a unit test for the Parser class.
 *
 * @author Kleon Ang
 */
public class ParserTest {
    /**
     * Test the parse method when a list command is given with an empty TaskList.
     */
    @Test
    public void parse_list_exceptionThrown() {
        TaskList taskList = new TaskList();
        Parser parser = new Parser(taskList);
        assertThrows(DukeException.class, () -> parser.parse("list"));
    }

    /**
     * Test the parse method when a new Deadline is added.
     *
     * @throws DukeException A Duke-specific exception that may occur when adding a new Deadline.
     */
    @Test
    public void parse_newDeadline_taskAdded() throws DukeException {
        TaskList taskList = new TaskList();
        Parser parser = new Parser(taskList);
        TaskList expectedTaskList = new TaskList();
        LocalDateTime by = LocalDateTime.parse("2021-09-04 14:00", DateTimeFormatter.ofPattern("yyyy-MM-d H:mm"));
        Deadline deadline = new Deadline("lab 1", by);
        expectedTaskList.add(deadline);
        assertEquals(expectedTaskList.toString(), parser.parse("deadline lab 1 /by 2021-09-04 14:00").toString());
    }
}
