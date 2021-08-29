import commands.AddCommand;
import commands.ByeCommand;
import commands.Command;
import commands.ListCommand;
import exceptions.DukeException;
import org.junit.jupiter.api.Test;
import viper.Parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandsTest {
    @Test
    public void addCommandsTest() throws DukeException {
        Command userInput1 = Parser.parse("todo homework");
        assertEquals(userInput1.getClass(), AddCommand.class);
        
        Command userInput2 = Parser.parse("deadline quiz 1 /by 2021-11-10");
        assertEquals(userInput2.getClass(), AddCommand.class);
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
