package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void test1() {
        String test = "BYE";
        assertEquals("bye", Parser.parseCommand(test));
    }

    @Test
    public void test2() {
        String test = "LiSt";
        assertEquals("list", Parser.parseCommand(test));
    }
}
