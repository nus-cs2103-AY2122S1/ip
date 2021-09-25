import org.junit.jupiter.api.Test;
import tokio.commands.AddDeadlineCommand;
import tokio.commands.AddEventCommand;
import tokio.commands.AddTodoCommand;
import tokio.commands.ByeCommand;
import tokio.commands.Command;
import tokio.commands.ListCommand;
import tokio.exceptions.DukeException;
import tokio.parser.Parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandsTest {
    @Test
    public void addCommandsTest() throws DukeException {
        Command userInput1 = Parser.parse("todo homework");
        assertEquals(userInput1.getClass(), AddTodoCommand.class);
        
        Command userInput2 = Parser.parse("deadline quiz 1 /by 2021-11-10");
        assertEquals(userInput2.getClass(), AddDeadlineCommand.class);

        Command userInput3 = Parser.parse("event meeting 1 /at 2021-11-10 12:00");
        assertEquals(userInput3.getClass(), AddEventCommand.class);
    }
    
    @Test
    public void byeCommandTest() throws DukeException {
        Command userInput2 = Parser.parse("bye");
        assertEquals(userInput2.getClass(), ByeCommand.class);
    }
    
    @Test
    public void listCommandTest() throws DukeException {
        Command userInput3 = Parser.parse("list");
        assertEquals(userInput3.getClass(), ListCommand.class);
    }
}
