package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.command.AddCommand;
import duke.exception.DukeException;

public class ParserTest {
    @Test
    public void parse_addTodo() {
        CommandParser<?> parser = new GlobalParser();
        try {
            AddCommand command = (AddCommand) parser.parse("todo /desc brush teeth");
            assertEquals("T | 0 | brush teeth", command.getTask().toDataString());
        } catch (DukeException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parse_todoNoDescription_exceptionThrown() {
        CommandParser<?> parser = new GlobalParser();
        try {
            parser.parse("todo ");
        } catch (DukeException e) {
            assertEquals("Sorry Boss, todo must have /desc argument", e.getMessage());
        }
    }

    @Test
    public void parse_incorrectCmd_exceptionThrown() {
        CommandParser<?> parser = new GlobalParser();
        try {
            parser.parse("incorrect");
        } catch (DukeException e) {
            assertEquals("Sorry Boss, I cannot understand the command", e.getMessage());
        }
    }
}
