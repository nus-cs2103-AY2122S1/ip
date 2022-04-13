package duke.io;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.ListCommand;
import duke.exceptions.AuguryException;
import duke.exceptions.InvalidActionException;

public class ParserTest {

    @Test
    public void getListCommandFromInput() throws AuguryException {
        Parser p = new Parser();
        Command c = p.parse("list");
        assertTrue(c instanceof ListCommand);
    }

    @Test
    public void getDeleteCommandFromInput() throws AuguryException {
        Parser p = new Parser();
        Command c = p.parse("delete 2,3");
        assertTrue(c instanceof DeleteCommand);
    }

    @Test
    public void invalidInput_nonInteger_exceptionThrown() throws AuguryException {
        Parser p = new Parser();
        Exception e = assertThrows(InvalidActionException.class, () -> {
            Command c = p.parse("delete 1,a,b,c");
        });

        String expectedMessage = "Please enter a valid task number.";
        String actualMessage = e.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void invalidInput_negativeInteger_exceptionThrown() throws AuguryException {
        Parser p = new Parser();
        Exception e = assertThrows(InvalidActionException.class, () -> {
            Command c = p.parse("delete 1,-999");
        });

        String expectedMessage = "Please enter a valid task number.";
        String actualMessage = e.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
