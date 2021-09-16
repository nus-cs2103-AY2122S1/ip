package task;

import exception.InvalidDateFormat;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EventTest {
    @Test
    public void testEventCreation() throws InvalidDateFormat {
        assertEquals("[E][ ] testDescription (at: 01 Jan 2021)\n",
                new Event("testDescription", "01/01/2021", "", false).toString());
    }

    @Test
    public void testDeadlineNotes() throws InvalidDateFormat {
        assertEquals("[E][ ] testDescription (at: 01 Jan 2021)\nnotes testing",
                new Event("testDescription", "01/01/2021", "notes testing", false).toString());
    }

    @Test
    public void testDeadlineDate() {
        Exception exception = assertThrows(InvalidDateFormat.class,
                () -> new Event("testDescription", "2021/01/01", "", false));
        assertEquals("Invalid format, please use the dd/mm/yyyy format for date.", exception.getMessage());
    }
}
