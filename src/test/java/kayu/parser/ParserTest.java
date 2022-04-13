package kayu.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import kayu.commands.ByeCommand;
import kayu.commands.Command;
import kayu.commands.DeadlineCommand;
import kayu.commands.DeleteCommand;
import kayu.commands.DoneCommand;
import kayu.commands.EmptyCommand;
import kayu.commands.EventCommand;
import kayu.commands.FindCommand;
import kayu.commands.InvalidCommand;
import kayu.commands.ListCommand;
import kayu.commands.TodoCommand;

public class ParserTest {

    private final Parser parser = new Parser();

    @Test
    public void testParseWithBye() {
        String input = ByeCommand.COMMAND_WORD;
        Command command = parser.parseToCommand(input);

        assertTrue(command instanceof ByeCommand);
        assertTrue(command.getCommandParams().isEmpty());
    }

    @Test
    public void testParseWithList() {
        String input = ListCommand.COMMAND_WORD;
        Command command = parser.parseToCommand(input);

        assertTrue(command instanceof ListCommand);
        assertTrue(command.getCommandParams().isEmpty());
    }

    @Test
    public void testParseWithDone() {
        String numberString = String.format("%d", 10);
        String input = DoneCommand.COMMAND_WORD + ' ' + numberString;
        Command command = parser.parseToCommand(input);

        assertTrue(command instanceof DoneCommand);
        assertEquals(numberString, command.getCommandParams());
    }

    @Test
    public void testParseWithFind() {
        String keyword = "test";
        String input = FindCommand.COMMAND_WORD + ' ' + keyword;
        Command command = parser.parseToCommand(input);

        assertTrue(command instanceof FindCommand);
        assertEquals(keyword, command.getCommandParams());
    }

    @Test
    public void testParseWithDelete() {
        String numberString = String.format("%d", 10);
        String input = DeleteCommand.COMMAND_WORD + ' ' + numberString;
        Command command = parser.parseToCommand(input);

        assertTrue(command instanceof DeleteCommand);
        assertEquals(numberString, command.getCommandParams());
    }

    @Test
    public void testParseWithTodo() {
        String params = "mock something";
        String input = TodoCommand.COMMAND_WORD + ' ' + params;
        Command command = parser.parseToCommand(input);

        assertTrue(command instanceof TodoCommand);
        assertEquals(params, command.getCommandParams());
    }

    @Test
    public void testParseWithEvent() {
        String params = "mock something /at 2020-11-115 10:00";
        String input = EventCommand.COMMAND_WORD + ' ' + params;
        Command command = parser.parseToCommand(input);

        assertTrue(command instanceof EventCommand);
        assertEquals(params, command.getCommandParams());
    }

    @Test
    public void testParseWithDeadline() {
        String params = "mock something /by 2020-11-115 10:00";
        String input = DeadlineCommand.COMMAND_WORD + ' ' + params;
        Command command = parser.parseToCommand(input);

        assertTrue(command instanceof DeadlineCommand);
        assertEquals(params, command.getCommandParams());
    }

    @Test
    public void testParseWithEmptyInput() {
        String input = "";
        Command command = parser.parseToCommand(input);

        assertTrue(command instanceof EmptyCommand);
        assertTrue(command.getCommandParams().isEmpty());
    }

    @Test
    public void testParseWithPureWhitespace() {
        String input = "         ";
        Command command = parser.parseToCommand(input);

        assertTrue(command instanceof EmptyCommand);
        assertTrue(command.getCommandParams().isEmpty());
    }

    @Test
    public void testParseWithInvalidInput() {
        String input = "gibberish";
        Command command = parser.parseToCommand(input);

        assertTrue(command instanceof InvalidCommand);
        assertTrue(command.getCommandParams().isEmpty());
    }
}
