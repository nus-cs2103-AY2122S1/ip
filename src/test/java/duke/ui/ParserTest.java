package duke.ui;

import duke.command.Command;
import duke.command.CommandType;
import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parse_addTodo() {
        Parser parser = new Parser();
        try {
            Command command = parser.parse("todo brush teeth");
            assertEquals(CommandType.ADD, command.type);
            assertEquals("[T][ ] brush teeth", command.payload.toString());
        } catch (DukeException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void parse_todoNoDescription() {
        Parser parser = new Parser();
        try {
            Command command = parser.parse("todo ");
        } catch (DukeException e) {
            assertEquals("Sorry Sir, todo must be followed with a description", e.getMessage());
        }
    }
    
    @Test
    public void parse_incorrectCmd() {
        Parser parser = new Parser();
        try {
            Command command = parser.parse("incorrect");
        } catch (DukeException e) {
            assertEquals("Sorry Sir, I cannot understand the command", e.getMessage());
        }
    }
}
