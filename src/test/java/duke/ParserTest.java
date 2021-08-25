package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void parse_illegalInput_exceptionThrown() {
        try {
            Parser.parse("illegal input");
            fail();
        } catch (Exception e) {
            assertEquals("☹ OOPS!!! I'm sorry, but I don't know what that means :-(", e.getMessage());
        }
    }

    @Test
    void parse_legalInput_success() {
        try {
            Parser.parse("todo ");
        } catch (Exception e) {
            fail();
        }
    }
}