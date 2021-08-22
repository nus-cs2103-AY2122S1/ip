package duke;

import duke.command.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void parse_validCommands() {
        Command c;
        try {
            c = Parser.parse("list");
            assertTrue(c instanceof ListCommand);
            c = Parser.parse("bye");
            assertTrue(c instanceof ExitCommand);
            c = Parser.parse("delete 1");
            assertTrue(c instanceof DeleteCommand);
            c = Parser.parse("done 1");
            assertTrue(c instanceof DoneCommand);
            c = Parser.parse("todo test");
            assertTrue(c instanceof AddCommand);
            c = Parser.parse("deadline test /by 2020-02-02");
            assertTrue(c instanceof AddCommand);
            c = Parser.parse("event test /at 2020-02-02");
            assertTrue(c instanceof AddCommand);

        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void parse_invalidCommands_exceptionThrown() {
        assertThrows(DukeException.class, () -> Parser.parse("INVALID COMMAND"));
        assertThrows(IllegalArgumentException.class, () -> Parser.parse("done"));
        assertThrows(IllegalArgumentException.class, () -> Parser.parse("delete"));
        assertThrows(IllegalArgumentException.class, () -> Parser.parse("todo"));
        assertThrows(IllegalArgumentException.class, () -> Parser.parse("deadline abcd"));
        assertThrows(IllegalArgumentException.class, () -> Parser.parse("event abcd"));
    }
}
