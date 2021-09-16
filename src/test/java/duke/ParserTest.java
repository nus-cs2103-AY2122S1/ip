package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.task.TaskList;

public class ParserTest {


    @Test
    public void parse_invalidCommand_dukeExceptionThrown() {
        TaskList tasks = new TaskList();
        Parser parser = new Parser(tasks);

        DukeException thrown = assertThrows(DukeException.class, () -> parser.parse("abcde"));
        assertEquals("You have entered an invalid command, please try again!",
                thrown.getMessage());
    }

    @Test
    public void parse_doneTaskEmptyNumber_dukeExceptionThrown() {
        TaskList tasks = new TaskList();
        Parser parser = new Parser(tasks);

        DukeException thrown = assertThrows(DukeException.class, () -> parser.parse("done"));
        assertEquals("Please enter the task number of your target task.",
                thrown.getMessage());
    }

}
