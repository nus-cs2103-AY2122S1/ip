package task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import exception.InvalidDateFormat;

public class DeadlineTest {
    @Test
    public void testDeadlineCreation() throws InvalidDateFormat {
        assertEquals("[D][ ] testDescription (by: 01 Jan 2021)\n",
                new Deadline("testDescription", "01/01/2021", "", false).toString());
    }

    @Test
    public void testDeadlineNotes() throws InvalidDateFormat {
        assertEquals("[D][ ] testDescription (by: 01 Jan 2021)\nnotes testing",
                new Deadline("testDescription", "01/01/2021", "notes testing", false).toString());
    }

    @Test
    public void testDeadlineDate() {
        Exception exception = assertThrows(InvalidDateFormat.class, () ->
                new Deadline("testDescription", "2021/01/01", "", false));
        assertEquals("Invalid format, please use the dd/mm/yyyy format for date.", exception.getMessage());
    }
}
