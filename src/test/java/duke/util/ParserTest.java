package duke.util;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import duke.command.ExitCommand;
import duke.exception.DukeException;

public class ParserTest {
    @Test
    public void testGetCommand_byeCommand_returnsExitCommand() {
        Parser parser = new Parser(new TaskList(null, null));
        assertTrue(parser.getCommand("bye") instanceof ExitCommand);
    }

    @Test
    public void testParseTestDescription() {
        assertEquals("return book", Parser.getTaskDescription("todo return book"));
    }

    @Test
    public void testParseRemindArguments_inputToday_returnsToday() {
        assertEquals("today", Parser.getRemindArgument("remind today"));
    }

    @Test
    public void testParseRemindArguments_inputWeek_returnsWeek() {
        assertEquals("week", Parser.getRemindArgument("remind week"));
    }

    @Test
    public void testParseRemindArguments_inputNext_returnsNext() {
        assertEquals("next", Parser.getRemindArgument("remind next"));
    }

    @Test
    public void testParseRemindArguments_invalidInput_throwsException() {
        try {
            assertEquals("tomorrow", Parser.getRemindArgument("remind tomorrow"));
            fail();
        } catch (DukeException e) {
            assertEquals("BAKA! I don't understand this argument in your command!\n"
                + "Argument: tomorrow\n" + "You might want to check for spelling and potential whitespaces!",
                e.getMessage()
            );
        }
    }

    @Test
    public void testParseTaskIndex() {
        assertEquals(4, Parser.getTaskIndex("done 4"));
    }

    @Test
    public void testParseMultipleTaskIndex() {
        assertArrayEquals(new int[] {4, 5}, Parser.getAllTaskIndexes("done 4 5"));
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
