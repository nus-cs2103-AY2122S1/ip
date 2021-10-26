package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.command.Command;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.exception.DukeException;

public class ParserTest {

    @Test
    public void parse_gibberishInput_exceptionThrown() {
        try {
            assertEquals(0, Parser.parse("asdf"));
            fail();
        } catch (DukeException e) {
            assertEquals("I'm sorry, but I don't know what that means :-(", e.getMessage());
        }
    }

    @Test
    public void parse_listCommand_parsedCorrectly() throws DukeException {
        final String input = "list";
        parseAndAssertCommandType(input, ListCommand.class);
    }

    @Test
    public void parse_exitCommand_parsedCorrectly() throws DukeException {
        final String input = "bye";
        parseAndAssertCommandType(input, ExitCommand.class);
    }

    //@@author nhjryan-reused
    //Reused from
    // https://github.com/se-edu/addressbook-level2/blob/master/test/java/seedu/addressbook/parser/ParserTest.java
    //with minor modifications
    private <T extends Command> T parseAndAssertCommandType(String input,
                                                            Class<T> expectedCommandClass) throws DukeException {
        final Command result = Parser.parse(input);
        assertTrue(result.getClass().isAssignableFrom(expectedCommandClass));
        return (T) result;
    }
}
