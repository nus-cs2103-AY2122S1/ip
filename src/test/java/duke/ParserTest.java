package duke;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.task.ToDo;



public class ParserTest {
    @Test
    public void parse_eventString_success() throws DukeException {
        TaskList tasks = new TaskList();
        try {
            Parser.parse("todo todo description").execute(tasks, null, null);
        } catch (NullPointerException e) {
            assertTrue(tasks.getIndex(0) instanceof ToDo);
        }
    }
}
