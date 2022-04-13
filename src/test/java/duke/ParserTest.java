package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.command.CommandUnknown;
import duke.exception.DukeException;
import duke.parser.Parser;

public class ParserTest {
    @Test
    public void parse_incompleteStringInput_dukeExceptionThrown() {
        try {
            Parser.parse("done");
            fail();
        } catch (DukeException e) {
            assertEquals("Insufficient input received! Please indicate the task number of the completed task.",
                    e.getMessage());
        }
    }

    @Test
    public void parse_unknownCommand_success() {
        try {
            assertEquals(new CommandUnknown(), Parser.parse("blah"));
        } catch (DukeException e) {
            fail();
        }

    }
}
