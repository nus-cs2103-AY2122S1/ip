package biscuit.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import biscuit.commands.Command;
import biscuit.exceptions.BiscuitException;

/**
 * Unit test for Parser class.
 */
class ParserTest {

    @Test
    void parse_invalidInput_exceptionThrown() {
        String input = "invalid input";
        assertThrows(BiscuitException.class, () -> Parser.parse(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"todo test", "event test /at 02-22-2021 22:02", "deadline test /by 02-22-2021"})
    void parse_addCommand_success(String input) throws BiscuitException {
        Command command = Parser.parse(input);
        assertEquals(Command.CommandType.ADD, command.getCommandType());
        assertFalse(command.isExit());
    }

    @Test
    void parse_deleteCommand_success() throws BiscuitException {
        String input = "delete 1";
        Command command = Parser.parse(input);
        assertEquals(Command.CommandType.DELETE, command.getCommandType());
        assertFalse(command.isExit());
    }

    @Test
    void parse_doneCommand_success() throws BiscuitException {
        String input = "done 1";
        Command command = Parser.parse(input);
        assertEquals(Command.CommandType.DONE, command.getCommandType());
        assertFalse(command.isExit());
    }

    @Test
    void parse_listCommand_success() throws BiscuitException {
        String input = "list";
        Command command = Parser.parse(input);
        assertEquals(Command.CommandType.LIST, command.getCommandType());
        assertFalse(command.isExit());
    }

    @Test
    void parse_exitCommand_success() throws BiscuitException {
        String input = "bye";
        Command command = Parser.parse(input);
        assertEquals(Command.CommandType.EXIT, command.getCommandType());
        assertTrue(command.isExit());
    }


}
