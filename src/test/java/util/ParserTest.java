package util;


import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import side.util.Parser;

public class ParserTest {
    @Test
    public void findDeadlineDatetimeTest() {
        Parser p = new Parser();
        assertEquals(p.findDeadlineDatetime("/by 2020-11-11"), "2020-11-11");
    }

    @Test
    public void findEventDatetimeTest() {
        Parser p = new Parser();
        String[] expected = {"2020-11-11", "2020-11-12"};
        assertArrayEquals(p.findEventDatetime("/at 2020-11-11 /to 2020-11-12"), expected);
    }

    @Test
    public void findDescriptionTest() {
        Parser p = new Parser();
        assertEquals(p.findDescription("deadline a /by 2020-11-11"), "a ");
        assertEquals(p.findDescription("event a /at 2020-11-11 /to 2020-11-12"), "a ");
    }

    @Test
    public void tryIntParsingTestValid() {
        Parser p = new Parser();
        assertEquals(p.tryIntParsing("1800"), 1800);
    }

    @Test
    public void toCommandTest() {
        assertEquals(Parser.toCommand("todo"), Parser.COMMAND.TODO);
        assertEquals(Parser.toCommand("deadline"), Parser.COMMAND.DEADLINE);
        assertEquals(Parser.toCommand("event"), Parser.COMMAND.EVENT);
        assertEquals(Parser.toCommand("list"), Parser.COMMAND.LIST);
        assertEquals(Parser.toCommand("done"), Parser.COMMAND.DONE);
        assertEquals(Parser.toCommand("delete"), Parser.COMMAND.DELETE);
        assertEquals(Parser.toCommand("sdc"), Parser.COMMAND.INVALID);
    }
}
