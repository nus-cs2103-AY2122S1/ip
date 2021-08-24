package biscuit.parser;


import biscuit.commands.Command;
import biscuit.exceptions.BiscuitException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParserTest {

    @Test
    void parse_invalidInput_exceptionThrown() {
        String input = "invalid input";
        assertThrows(BiscuitException.class, () -> Parser.parse(input));
    }

    @Test
    void parse_toDoCommand_success() throws BiscuitException {
        String input = "todo test";
        Command command = Parser.parse(input);
        assertEquals(Command.CommandType.ADD, command.getCommandType());
        assertFalse(command.isExit());
    }

    @Test
    void parse_eventCommand_success() throws BiscuitException {
        String input = "event test /at 02-22-2021 22:02";
        Command command = Parser.parse(input);
        assertEquals(Command.CommandType.ADD, command.getCommandType());
        assertFalse(command.isExit());
    }

    @Test
    void parse_deadlineCommand_success() throws BiscuitException {
        String input = "deadline test /by 02-22-2021";
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