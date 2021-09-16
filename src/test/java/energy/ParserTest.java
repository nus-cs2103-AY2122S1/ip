package energy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import energy.command.AddTaskCommand;
import energy.command.ByeCommand;
import energy.command.DeleteTaskCommand;
import energy.command.DoneCommand;
import energy.command.DueCommand;
import energy.command.ListCommand;
import energy.command.OnDateCommand;
import energy.util.Parser;

public class ParserTest {
    @Test
    public void parseDateFromInput_incorrectFormat_exceptionThrown() {
        try {
            Parser.parseDateFromInput("2021-021-10");
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("Date must be of the form YYYY-MM-DD, and must be a real/valid date.", e.getMessage());
        }
    }

    @Test
    public void parseDateFromInput_invalidDate_exceptionThrown() {
        try {
            Parser.parseDateFromInput("2021-26-10");
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("Date must be of the form YYYY-MM-DD, and must be a real/valid date.", e.getMessage());
        }
    }

    @Test
    public void parseDateFromInput_success() {
        try {
            String timeString = "2021-02-16";
            assertEquals(LocalDate.parse(timeString), Parser.parseDateFromInput(timeString));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parseTimeFromInput_incorrectFormat_exceptionThrown() {
        try {
            Parser.parseTimeFromInput("0:11");
            fail();
        } catch (Exception e) {
            assertEquals("Time must be of the form HH:MM. (HH from 00-23, MM from 00-59)", e.getMessage());
        }
    }

    @Test
    public void parseTimeFromInput_invalidTime_exceptionThrown() {
        try {
            Parser.parseTimeFromInput("25:11");
            fail();
        } catch (Exception e) {
            assertEquals("Time must be of the form HH:MM. (HH from 00-23, MM from 00-59)", e.getMessage());
        }
    }

    @Test
    public void parseTimeFromInput_success() {
        try {
            String timeString = "11:11";
            assertEquals(LocalTime.parse(timeString), Parser.parseTimeFromInput(timeString));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parseCommandFromInput_invalidCommand_exceptionThrown() {
        try {
            Parser.parseCommandFromInput("blah blah blah");
            fail();
        } catch (Exception e) {
            assertEquals("You have entered an invalid command.", e.getMessage());
        }
    }

    @Test
    public void parseCommandFromInput_byeCommand_success() {
        try {
            assertTrue(Parser.parseCommandFromInput("bye") instanceof ByeCommand);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parseCommandFromInput_listCommand_success() {
        try {
            assertTrue(Parser.parseCommandFromInput("list") instanceof ListCommand);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parseCommandFromInput_doneCommand_success() {
        try {
            assertTrue(Parser.parseCommandFromInput("done") instanceof DoneCommand);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parseCommandFromInput_todoCommand_success() {
        try {
            assertTrue(Parser.parseCommandFromInput("todo") instanceof AddTaskCommand);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parseCommandFromInput_deadlineCommand_success() {
        try {
            assertTrue(Parser.parseCommandFromInput("deadline") instanceof AddTaskCommand);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parseCommandFromInput_eventCommand_success() {
        try {
            assertTrue(Parser.parseCommandFromInput("event") instanceof AddTaskCommand);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parseCommandFromInput_deleteCommand_success() {
        try {
            assertTrue(Parser.parseCommandFromInput("delete") instanceof DeleteTaskCommand);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parseCommandFromInput_onDateCommand_success() {
        try {
            assertTrue(Parser.parseCommandFromInput("ondate") instanceof OnDateCommand);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parseCommandFromInput_dueCommand_success() {
        try {
            assertTrue(Parser.parseCommandFromInput("due") instanceof DueCommand);
        } catch (Exception e) {
            fail();
        }
    }
}
