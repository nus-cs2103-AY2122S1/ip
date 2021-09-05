package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ParserTest {

    @Test
    public void testParse() {
        try {
            assertEquals("", Parser.parse("bleh"));
        } catch (Exception e) {
            assertEquals("Invalid Command!", e.getMessage());
        }
    }

    @Test
    public void parse_exitCommand_isExitTrue() throws Exception {
        assertEquals(true, Parser.parse("bye").isExit());
    }
}
