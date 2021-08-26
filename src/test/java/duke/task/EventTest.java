package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.format.DateTimeParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void testStringConversion() {
        assertEquals("[E][ ] This is a test (at:Aug 25 2021 02:00 PM)",
            new Event("This is a test", "25/08/2021 1400").toString());
        assertEquals("[E][X] This is a test (at:Aug 25 2021 02:00 PM)",
            new Event("This is a test", true, "25/08/2021 1400").toString());
    }

    @Test
    public void testStorageFormatConversion() {
        assertEquals("E%This is a test%false%25/08/2021 1400",
            new Event("This is a test", "25/08/2021 1400").toStorageFormat());
        assertEquals("E%This is a test%true%25/08/2021 1400",
            new Event("This is a test", true, "25/08/2021 1400").toStorageFormat());
    }

    @Test
    public void eventCreation_missingTime_exceptionThrown() {
        Assertions.assertThrows(DateTimeParseException.class, () -> {
            new Event("Missing time", "25/08/2021");
        });
    }
}
