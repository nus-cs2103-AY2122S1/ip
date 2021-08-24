package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    Parser parser = new Parser();

    @Test
    public void testOneWord() {
        assertEquals(parser.parse("list").startsWith(), "list");
        assertEquals(parser.parse("list 5").startsWith(), "list");
        assertFalse(parser.parse("list").isExit());

        assertEquals(parser.parse("formats").startsWith(), "formats");
        assertEquals(parser.parse("formats 5").startsWith(), "formats");
        assertFalse(parser.parse("formats").isExit());

        assertEquals(parser.parse("bye").startsWith(), "bye");
        assertEquals(parser.parse("bye 5").startsWith(), "bye");
        assertTrue(parser.parse("bye").isExit());
    }

    @Test void testOneArg() {
        assertEquals(parser.parse("done").startsWith(), "done");
        assertEquals(parser.parse("done 5").startsWith(), "done");
        assertEquals(parser.parse("done 5").getArguments(), "5");
        assertFalse(parser.parse("done 5").isExit());
    }
}
