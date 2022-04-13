package duke.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.DoneCommand;
import duke.exception.DukeException;

public class ParserTest {
    @Test
    public void identifyCommand_emptyString_exceptionThrown() {
        try {
            assertEquals(null, Parser.identifyCommand(""));
            fail();
        } catch (Exception e) {
            assertEquals("Go on, I'm all ears!", e.getMessage());
        }
    }

    @Test
    public void identifyCommand_unrecognisedCommand_exceptionThrown() {
        try {
            Parser.identifyCommand("jksbfaskj bafjkb bjaskfb");
            fail();
        } catch (DukeException e) {
            assertEquals("I don't understand what you mean :(",
                    e.getMessage());
        }
    }

    @Test
    public void identifyCommand_validDoneCommand_doneCommandReturned() {
        try {
            assertEquals(true,
                    Parser.identifyCommand("done 23") instanceof DoneCommand);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void identifyCommand_validDeadlineCommand_deadlineCommandReturned() {
        try {
            assertEquals(true,
                    Parser.identifyCommand(
                            "deadline walk my cat /by 2/7/2010 1243") instanceof AddDeadlineCommand);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void identifyCommand_validEventCommand_eventCommandReturned() {
        try {
            assertEquals(true,
                    Parser.identifyCommand(
                            "event walk my dog /at 20/8/2019 1200") instanceof AddEventCommand);
        } catch (DukeException e) {
            fail();
        }
    }
}
