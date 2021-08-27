package duke.util;

import duke.command.ExitCommand;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    @Test
    public void testGetCommand_byeCommand_returnsExitCommand() {
        Parser parser = new Parser(new TaskList(null, null));
        assertTrue(parser.getCommand("bye") instanceof ExitCommand);
    }

    @Test
    public void testParseTestDescription() {
        assertEquals("return book", Parser.filterTaskDescription("todo return book"));
    }

    @Test
    public void testParseTaskIndex() {
        assertEquals(4, Parser.filterTaskIndex("done 4"));
    }

    @Test
    public void testParseDate() {
        assertEquals(LocalDate.parse("2021-08-21"), Parser.parseDate("21/08/2021"));
    }

    @Test
    public void testParseTime() {
        assertEquals(LocalTime.parse("21:00"), Parser.parseTime("2100"));
    }

    @Test
    public void testParseDeadlineDate() {
        assertArrayEquals(new String[] {"return book", "21/08/2021 2100"},
                Parser.parseDeadlineDate("return book /by 21/08/2021 2100"));
    }

    @Test
    public void testParseEventDate() {
        assertArrayEquals(new String[] {"project meeting", "21/08/2021 1400 1600"},
                Parser.parseEventDate("project meeting /at 21/08/2021 1400 1600"));
    }
}