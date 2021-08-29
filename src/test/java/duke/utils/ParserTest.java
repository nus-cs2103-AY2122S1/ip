package duke.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.exceptions.EmptyTaskDescriptionException;
import duke.exceptions.UnknownInputException;

public class ParserTest {
    @Test
    public void testEmptyDescriptionError_forMultipleDifferentCommands_exceptionThrown() {
        String cmdOne = "This is a random command that should throw an exception";
        String cmdTwo = "deadline";
        String cmdThree = "todo";
        String cmdFour = "event";
        String cmdFive = "sdjkhgkdjl";

        try {
            Parser.parse(cmdOne);
            fail();
        } catch (UnknownInputException e) {
            assertEquals("Rasengan!! I don't know what that means :-(", e.getMessage());
        } catch (EmptyTaskDescriptionException e1) {
            fail();
        }

        try {
            Parser.parse(cmdTwo);
            fail();
        } catch (EmptyTaskDescriptionException e) {
            assertEquals("Rasengan!! The description of a deadline cannot be empty!", e.getMessage());
        } catch (UnknownInputException e1) {
            fail();
        }

        try {
            Parser.parse(cmdThree);
            fail();
        } catch (EmptyTaskDescriptionException e) {
            assertEquals("Rasengan!! The description of a todo cannot be empty!", e.getMessage());
        } catch (UnknownInputException e1) {
            fail();
        }

        try {
            Parser.parse(cmdFour);
            fail();
        } catch (EmptyTaskDescriptionException e) {
            assertEquals("Rasengan!! The description of a event cannot be empty!", e.getMessage());
        } catch (UnknownInputException e1) {
            fail();
        }

        try {
            Parser.parse(cmdFive);
            fail();
        } catch (EmptyTaskDescriptionException e) {
            fail();
        } catch (UnknownInputException e1) {
            assertEquals("Rasengan!! I don't know what that means :-(", e1.getMessage());
        }

    }

    @Test
    public void testCorrectCommandReturnedFromParser_success() {
        String cmdOne = "todo read book";
        String cmdTwo = "deadline return book /by 2021-10-11";
        String cmdThree = "list";
        String cmdFour = "bye";
        String cmdFive = "event project meeting /at Aug 6th 2-4pm";

        try {
            Command c = Parser.parse(cmdOne);
            assertTrue(c instanceof Command.ToDoCommand);
        } catch (DukeException e) {
            fail();
        }

        try {
            Command c = Parser.parse(cmdTwo);
            assertTrue(c instanceof Command.DeadlineCommand);
        } catch (DukeException e) {
            fail();
        }

        try {
            Command c = Parser.parse(cmdThree);
            assertTrue(c instanceof Command.ListAllCommand);
        } catch (DukeException e) {
            fail();
        }

        try {
            Command c = Parser.parse(cmdFour);
            assertTrue(c instanceof Command.ExitCommand);
        } catch (DukeException e) {
            fail();
        }

        try {
            Command c = Parser.parse(cmdFive);
            assertTrue(c instanceof Command.EventCommand);
        } catch (DukeException e) {
            fail();
        }

    }
}
