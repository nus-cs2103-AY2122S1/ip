package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;


public class ParserTest {
    @Test
    public void parseInput_invalidCommand_exceptionThrown() {
        try {
            assertEquals(0, Parser.parse("blah"));
            fail();
        } catch (Exception e) {
            assertEquals("OOPS!!! I'm sorry, but I don't know what that means :(", e.toString());
        }
    }

    @Test
    public void parseInput_emptyDescription_exceptionThrown() {
        try {
            assertEquals(0, Parser.parse("todo"));
            fail(); //test should not reach this line
        } catch (Exception e) {
            assertEquals("OOPS!!! The description cannot be empty.", e.toString());
        }
    }
}
