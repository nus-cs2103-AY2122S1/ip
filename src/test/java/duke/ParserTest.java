package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.command.AddTaskCommand;
import duke.command.Command;

public class ParserTest {

    @Test
    public void testParserOutput() {
        Command c = Parser.parse("todo test");
        assertEquals(c instanceof AddTaskCommand, true);
    }
}
