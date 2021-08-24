package kayu.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static kayu.commands.CommandType.BYE;
import static kayu.commands.CommandType.DEADLINE;
import static kayu.commands.CommandType.DELETE;
import static kayu.commands.CommandType.DONE;
import static kayu.commands.CommandType.EMPTY;
import static kayu.commands.CommandType.EVENT;
import static kayu.commands.CommandType.FIND;
import static kayu.commands.CommandType.INVALID;
import static kayu.commands.CommandType.LIST;
import static kayu.commands.CommandType.TODO;

import kayu.commands.ByeCommand;
import kayu.commands.Command;
import kayu.commands.DeadlineCommand;
import kayu.commands.DeleteCommand;
import kayu.commands.DoneCommand;
import kayu.commands.EventCommand;
import kayu.commands.FindCommand;
import kayu.commands.ListCommand;
import kayu.commands.TodoCommand;
import org.junit.jupiter.api.Test;

public class ParserTest {
    
    private final Parser parser = new Parser();
    
    @Test
    public void testParseWithBye() {
        String input = ByeCommand.COMMAND_WORD;
        Command command = parser.parseToCommand(input);
        
        assertEquals(BYE, command.getCommandType());
        assertTrue(command.getCommandParams().isEmpty());
    }
    
    @Test
    public void testParseWithList() {
        String input = ListCommand.COMMAND_WORD;
        Command command = parser.parseToCommand(input);
        
        assertEquals(LIST, command.getCommandType());
        assertTrue(command.getCommandParams().isEmpty());
    }
    
    @Test
    public void testParseWithDone() {
        String numberString = String.format("%d", 10);
        String input = DoneCommand.COMMAND_WORD + ' ' + numberString;
        Command command = parser.parseToCommand(input);
        
        assertEquals(DONE, command.getCommandType());
        assertEquals(numberString, command.getCommandParams());
    }

    @Test
    public void testParseWithFind() {
        String keyword = "test";
        String input = FindCommand.COMMAND_WORD + ' ' + keyword;
        Command command = parser.parseToCommand(input);

        assertEquals(FIND, command.getCommandType());
        assertEquals(keyword, command.getCommandParams());
    }
    
    @Test
    public void testParseWithDelete() {
        String numberString = String.format("%d", 10);
        String input = DeleteCommand.COMMAND_WORD + ' ' + numberString;
        Command command = parser.parseToCommand(input);
        
        assertEquals(DELETE, command.getCommandType());
        assertEquals(numberString, command.getCommandParams());
    }
    
    @Test
    public void testParseWithTodo() {
        String params = "mock something";
        String input = TodoCommand.COMMAND_WORD + ' ' + params;
        Command command = parser.parseToCommand(input);
        
        assertEquals(TODO, command.getCommandType());
        assertEquals(params, command.getCommandParams());
    }

    @Test
    public void testParseWithEvent() {
        String params = "mock something /at 2020-11-115 10:00";
        String input = EventCommand.COMMAND_WORD + ' ' + params;
        Command command = parser.parseToCommand(input);

        assertEquals(EVENT, command.getCommandType());
        assertEquals(params, command.getCommandParams());
    }

    @Test
    public void testParseWithDeadline() {
        String params = "mock something /by 2020-11-115 10:00";
        String input = DeadlineCommand.COMMAND_WORD + ' ' + params;
        Command command = parser.parseToCommand(input);

        assertEquals(DEADLINE, command.getCommandType());
        assertEquals(params, command.getCommandParams());
    }
    
    @Test
    public void testParseWithEmptyInput() {
        String input = "";
        Command command = parser.parseToCommand(input);
        
        assertEquals(EMPTY, command.getCommandType());
        assertTrue(command.getCommandParams().isEmpty());
    }

    @Test
    public void testParseWithPureWhitespace() {
        String input = "         ";
        Command command = parser.parseToCommand(input);
        
        assertEquals(EMPTY, command.getCommandType());
        assertTrue(command.getCommandParams().isEmpty());
    }

    @Test
    public void testParseWithInvalidInput() {
        String input = "gibberish";
        Command command = parser.parseToCommand(input);
        
        assertEquals(INVALID, command.getCommandType());
        assertTrue(command.getCommandParams().isEmpty());
    }

    @Test
    public void testParseWithImproperFormat() {
        String input = "todo"; // requires params
        Command command = parser.parseToCommand(input);
        
        assertEquals(INVALID, command.getCommandType());
        assertTrue(command.getCommandParams().isEmpty());
    }
}
