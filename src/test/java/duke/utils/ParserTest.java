package duke.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.EventCommand;
import duke.commands.ExitCommand;
import duke.commands.ListAllCommand;
import duke.commands.ToDoCommand;
import duke.exceptions.DukeException;
import duke.exceptions.EmptyTaskDescriptionException;
import duke.exceptions.UnknownInputException;

public class ParserTest {
    @Test
    public void testEmptyDescriptionError_forDifferentCommands_exceptionThrown1() {
        String cmdOne = "This is a random command that should throw an exception";

        try {
            Parser.parse(cmdOne);
            fail();
        } catch (UnknownInputException e) {
            assertEquals("Rasengan!! I don't know what that means :-(", e.getMessage());
        } catch (EmptyTaskDescriptionException e1) {
            fail();
        }
    }

    @Test
    public void testEmptyDescriptionError_forDifferentCommands_exceptionThrown2() {
        String cmdOne = "event";
        try {
            Parser.parse(cmdOne);
            fail();
        } catch (EmptyTaskDescriptionException e) {
            assertEquals("Rasengan!! The description of a event cannot be empty!", e.getMessage());
        } catch (UnknownInputException e1) {
            fail();
        }
    }

    @Test
    public void testEmptyDescriptionError_forDifferentCommands_exceptionThrown3() {
        String cmdOne = "todo";
        try {
            Parser.parse(cmdOne);
            fail();
        } catch (EmptyTaskDescriptionException e) {
            assertEquals("Rasengan!! The description of a todo cannot be empty!", e.getMessage());
        } catch (UnknownInputException e1) {
            fail();
        }
    }

    @Test
    public void testCorrectCommandReturnedFromParser_success1() {
        String cmdOne = "todo read book";

        try {
            Command c = Parser.parse(cmdOne);
            assertTrue(c instanceof ToDoCommand);
        } catch (DukeException e) {
            fail();
        }

    }

    @Test
    public void testCorrectCommandReturnedFromParser_success2() {
        String cmdTwo = "deadline return book /by 2021-10-11";
        try {
            Command c = Parser.parse(cmdTwo);
            assertTrue(c instanceof DeadlineCommand);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void testCorrectCommandReturnedFromParser_success3() {
        String cmdThree = "list";
        try {
            Command c = Parser.parse(cmdThree);
            assertTrue(c instanceof ListAllCommand);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void testCorrectCommandReturnedFromParser_success4() {
        String cmdFour = "bye";
        try {
            Command c = Parser.parse(cmdFour);
            assertTrue(c instanceof ExitCommand);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void testCorrectCommandReturnedFromParser_success5() {
        String cmdFive = "event project meeting /at Aug 6th 2-4pm";
        try {
            Command c = Parser.parse(cmdFive);
            assertTrue(c instanceof EventCommand);
        } catch (DukeException e) {
            fail();
        }
    }
}
