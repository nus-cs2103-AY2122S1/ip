import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lania.Parser;
import lania.exception.LaniaEmptyDescriptionException;
import lania.task.Deadline;
import lania.task.Event;
import lania.task.Task;
import lania.task.Todo;

public class LaniaTest {
    @Test
    public void taskStringConversion() {
        Task a = new Todo("sleep");
        Task b = new Deadline("sleep", "24-08-2021 18:00");
        Task c = new Event("sleep", "24-08-2021 18:00");
        assertEquals(a.toString(), "[T][ ] sleep");
        assertEquals(b.toString(), "[D][ ] sleep (by: Aug 24 2021 6:00PM)");
        assertEquals(c.toString(), "[E][ ] sleep (at: Aug 24 2021 6:00PM)");
    }

    @Test
    public void parseTaskDescription_description_success() {
        Parser parser = new Parser();
        String a = "deadline read book /by 24-08-2021 18:00";
        String b = "event read book /by 24-08-2021 18:00";
        String c = "todo read book";
        try {
            assertEquals(parser.parseTaskDescription(a), "read book /by 24-08-2021 18:00");
            assertEquals(parser.parseTaskDescription(b), "read book /by 24-08-2021 18:00");
            assertEquals(parser.parseTaskDescription(c), "read book");
        } catch (LaniaEmptyDescriptionException e) {
            System.out.println("Error");
        }
    }

    @Test
    public void parseTaskDescription_emptyDescription_exceptionThrown() {
        Parser parser = new Parser();
        String d = "todo";
        try {
            parser.parseTaskDescription(d);
        } catch (LaniaEmptyDescriptionException e) {
            assertEquals(e.getMessage(), "The description of todo cannot be empty");
        }
    }

    @Test
    public void parseEventDeadline_dateTime_success() {
        Parser parser = new Parser();
        String a = "read book /by 24-08-2021 18:00";
        String b = "read book /at 24-08-2021 18:00";
        try {
            assertEquals(parser.parseDeadlineDescription(a)[0], "read book");
            assertEquals(parser.parseDeadlineDescription(a)[1], "24-08-2021 18:00");
            assertEquals(parser.parseEventDescription(b)[0], "read book");
            assertEquals(parser.parseEventDescription(b)[1], "24-08-2021 18:00");
        } catch (LaniaEmptyDescriptionException e) {
            System.out.println("Error");
        }

    }

    @Test
    public void parseEventDeadline_emptyDateTime_exceptionThrown() {
        Parser parser = new Parser();
        String c = "read book";
        String d = "borrow book";
        try {
            parser.parseEventDescription(c);
        } catch (LaniaEmptyDescriptionException e) {
            assertEquals(e.getMessage(), "The description of date/time cannot be empty");
        }
        try {
            parser.parseDeadlineDescription(d);
        } catch (LaniaEmptyDescriptionException e) {
            assertEquals(e.getMessage(), "The description of date/time cannot be empty");
        }
    }
}
