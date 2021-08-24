package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.CommandType;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.EventCommand;
import duke.commands.ListCommand;
import duke.commands.TodoCommand;
import org.junit.jupiter.api.Test;

public class ParserTest {
    
    private final Parser parser = new Parser();
    
    @Test
    public void testParseWithBye() {
        String input = ByeCommand.COMMAND_WORD;
        Command command = parser.parseToCommand(input);
        
        assertEquals(CommandType.BYE, command.getCommandType());
        assertTrue(command.getCommandParams().isEmpty());
    }
    
    @Test
    public void testParseWithList() {
        String input = ListCommand.COMMAND_WORD;
        Command command = parser.parseToCommand(input);
        
        assertEquals(CommandType.LIST, command.getCommandType());
        assertTrue(command.getCommandParams().isEmpty());
    }
    
    @Test
    public void testParseWithDone() {
        String numberString = String.format("%d", 10);
        String input = DoneCommand.COMMAND_WORD + ' ' + numberString;
        Command command = parser.parseToCommand(input);
        
        assertEquals(CommandType.DONE, command.getCommandType());
        assertEquals(numberString, command.getCommandParams());
    }
    
    @Test
    public void testParseWithDelete() {
        String numberString = String.format("%d", 10);
        String input = DeleteCommand.COMMAND_WORD + ' ' + numberString;
        Command command = parser.parseToCommand(input);
        
        assertEquals(CommandType.DELETE, command.getCommandType());
        assertEquals(numberString, command.getCommandParams());
    }
    
    @Test
    public void testParseWithTodo() {
        String params = "mock something";
        String input = TodoCommand.COMMAND_WORD + ' ' + params;
        Command command = parser.parseToCommand(input);
        
        assertEquals(CommandType.TODO, command.getCommandType());
        assertEquals(params, command.getCommandParams());
    }

    @Test
    public void testParseWithEvent() {
        String params = "mock something /at 2020-11-115 10:00";
        String input = EventCommand.COMMAND_WORD + ' ' + params;
        Command command = parser.parseToCommand(input);

        assertEquals(CommandType.EVENT, command.getCommandType());
        assertEquals(params, command.getCommandParams());
    }

    @Test
    public void testParseWithDeadline() {
        String params = "mock something /by 2020-11-115 10:00";
        String input = DeadlineCommand.COMMAND_WORD + ' ' + params;
        Command command = parser.parseToCommand(input);

        assertEquals(CommandType.DEADLINE, command.getCommandType());
        assertEquals(params, command.getCommandParams());
    }
    
    @Test
    public void testParseWithEmptyInput() {
        String input = "";
        Command command = parser.parseToCommand(input);
        
        assertEquals(CommandType.EMPTY, command.getCommandType());
        assertTrue(command.getCommandParams().isEmpty());
    }

    @Test
    public void testParseWithPureWhitespace() {
        String input = "         ";
        Command command = parser.parseToCommand(input);
        
        assertEquals(CommandType.EMPTY, command.getCommandType());
        assertTrue(command.getCommandParams().isEmpty());
    }

    @Test
    public void testParseWithInvalidInput() {
        String input = "gibberish";
        Command command = parser.parseToCommand(input);
        
        assertEquals(CommandType.INVALID, command.getCommandType());
        assertTrue(command.getCommandParams().isEmpty());
    }

    @Test
    public void testParseWithImproperFormat() {
        String input = "todo"; // requires params
        Command command = parser.parseToCommand(input);
        
        assertEquals(CommandType.INVALID, command.getCommandType());
        assertTrue(command.getCommandParams().isEmpty());
    }
}
