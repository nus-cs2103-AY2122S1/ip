package duke;

import duke.Exception.DukeException;
import duke.task.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    TaskList tasks = new TaskList();
    Parser parser = new Parser(tasks);

    @Test
    public void parse_invalidCommand_dukeExceptionThrown() {
        DukeException thrown = assertThrows(DukeException.class, () -> parser.parse("abcde"));
        assertEquals("\t☹ OOPS!!! You have entered an invalid command, please try again!",
                thrown.getMessage());
    }

    @Test
    public void parse_doneTaskEmptyNumber_dukeExceptionThrown() {
        DukeException thrown = assertThrows(DukeException.class, () -> parser.parse("done "));
        assertEquals("\t☹ OOPS!!! Please specify the task number for the task you want to complete.",
                thrown.getMessage());
    }

}
