package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    Parser parser = new Parser();

    @Test
    public void testOneWord() {
        assertEquals(parser.parse("list").getCommandPrefix(), "list");
        assertEquals(parser.parse("list 5").getCommandPrefix(), "list");
        assertFalse(parser.parse("list").isExit());

        assertEquals(parser.parse("formats").getCommandPrefix(), "formats");
        assertEquals(parser.parse("formats 5").getCommandPrefix(), "formats");
        assertFalse(parser.parse("formats").isExit());

        assertEquals(parser.parse("bye").getCommandPrefix(), "bye");
        assertEquals(parser.parse("bye 5").getCommandPrefix(), "bye");
        assertTrue(parser.parse("bye").isExit());
    }

    @Test void testOneArg() {
        assertEquals(parser.parse("done").getCommandPrefix(), "done");
        assertEquals(parser.parse("done 5").getCommandPrefix(), "done");
        assertEquals(parser.parse("done 5").getArguments(), "5");
        assertFalse(parser.parse("done 5").isExit());
    }
}
