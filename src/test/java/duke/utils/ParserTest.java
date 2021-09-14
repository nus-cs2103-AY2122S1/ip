package duke.utils;

import duke.commands.AddCommand;
import duke.commands.ExitCommand;
import duke.exceptions.InvalidInputException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {

    @Test
    public void parseTest_exit_exitCommandReturned(){
        try {
            assertTrue(Parser.parse("bye") instanceof ExitCommand);
        } catch (InvalidInputException e) {
            Assertions.fail();
        }
    }

    @Test
    public void parseTest_invalidInput_invalidInputExceptionThrown(){
        Assertions.assertThrows(InvalidInputException.class, () -> Parser.parse("hehexd"));
    }


    @Test
    public void parseDeadlineCommandTest_validInput_addCommandReturned(){
        try {
            assertTrue(Parser.parseDeadlineCommand("assignment 1 /by 2021-12-05") instanceof AddCommand);
        } catch (InvalidInputException e) {
            Assertions.fail();
        }
    }

    @Test
    public void parseDeadlineCommandTest_noDate_invalidInputExceptionThrown(){
        Assertions.assertThrows(InvalidInputException.class, () -> Parser.parseDeadlineCommand("hehexd"));
    }

    @Test
    public void parseDeadlineCommandTest_invalidDateFormat_invalidInputExceptionThrown(){
        Assertions.assertThrows(InvalidInputException.class,
                () -> Parser.parseDeadlineCommand("hehexd /by 2020/2/2"));
    }

    @Test
    public void parseEventCommandTest_validInput_addCommandReturned(){
        try {
            assertTrue(Parser.parseEventCommand(
                    "assignment 1 /from 2021-12-05 /to 2021-12-05") instanceof AddCommand);
        } catch (InvalidInputException e) {
            Assertions.fail();
        }
    }

    @Test
    public void parseEventCommandTest_noStartDate_invalidInputExceptionThrown(){
        Assertions.assertThrows(InvalidInputException.class,
                () -> Parser.parseEventCommand("hehexd /to 2020-05-10"));
    }

    @Test
    public void parseEventCommandTest_noEndDate_invalidInputExceptionThrown(){
        Assertions.assertThrows(InvalidInputException.class,
                () -> Parser.parseEventCommand("hehexd /from 2020-05-10"));
    }

    @Test
    public void parseEventCommandTest_invalidDateFormat_invalidInputExceptionThrown(){
        Assertions.assertThrows(InvalidInputException.class,
                () -> Parser.parseEventCommand("hehexd /from 2020/05/10 /to 2020/05/10"));
    }
    
}
