package duke.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.command.AddCommand;
import duke.exception.DukeException;
import duke.parser.Parser;

public class ParserTest {
    @Test
    public void parse_addTodo() {
        Parser parser = new Parser();
        try {
            AddCommand command = (AddCommand) parser.parse("todo brush teeth");
            assertEquals("[T][ ] brush teeth", command.getTask().toString());
        } catch (DukeException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parse_todoNoDescription_exceptionThrown() {
        Parser parser = new Parser();
        try {
            parser.parse("todo ");
        } catch (DukeException e) {
            assertEquals("Sorry Sir, todo must be followed with a description", e.getMessage());
        }
    }

    @Test
    public void parse_incorrectCmd_exceptionThrown() {
        Parser parser = new Parser();
        try {
            parser.parse("incorrect");
        } catch (DukeException e) {
            assertEquals("Sorry Sir, I cannot understand the command", e.getMessage());
        }
    }
}
