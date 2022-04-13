package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exception.InvalidCommandException;

public class ParserTest {
    @Test
    public void testInterpretCommand_normalInputs() throws InvalidCommandException {
        Parser parser = new Parser();
        assertEquals("list", parser.interpretCommand("list"));
        assertEquals("mark", parser.interpretCommand("done 2"));
        assertEquals("todo", parser.interpretCommand("todo fishing"));
        assertEquals("deadline", parser.interpretCommand("deadline cake /by 2020-03-03"));
        assertEquals("event", parser.interpretCommand("event homework /at 2020-03-03 5pm"));
    }

    @Test
    public void testInterpretCommand_unknownCommand() throws InvalidCommandException {
        Parser parser = new Parser();
        assertEquals("invalid", parser.interpretCommand("aaaaaaa"));
        assertEquals("invalid", parser.interpretCommand("list 3123"));
        assertEquals("invalid", parser.interpretCommand("frogfish"));
    }
}
