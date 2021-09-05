package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.command.Command;


public class CommandParserTest {
    @Test
    public void isExit_bye_true() {
        assertEquals(true, CommandParser.isExit("bye"));
    }

    @Test
    public void parse_invalidAddDeadlineCommand_dukeExceptionthrown() {
        try {
            Command command = CommandParser.parse("deadline homework");
            fail();
        } catch (DukeException e) {
            assertEquals(
                    "Please indicate in this format: deadline [description] /by [due date].",
                    e.getMessage());
        }
    }

    @Test
    public void parse_eventWithInvalidDateTime_dukeExceptionThrown() {
        try {
            Command command = CommandParser.parse("event meeting /at 2021.11.11 2000");
            fail();
        } catch (DukeException e) {
            assertEquals(
                    "Please provide date time in the format yyyy-MM-dd HHmm, e.g. 2021-08-04 2359",
                    e.getMessage());
        }
    }
}
