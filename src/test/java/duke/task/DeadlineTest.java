package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.format.DateTimeParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void testStringConversion() {
        assertEquals("[D][ ] This is a test (by:Aug 25 2021 02:00 PM)",
            new Deadline("This is a test", "25/08/2021 1400").toString());
        assertEquals("[D][X] This is a test (by:Aug 25 2021 02:00 PM)",
            new Deadline("This is a test", true, "25/08/2021 1400").toString());
    }

    @Test
    public void testStorageFormatConversion() {
        assertEquals("D%This is a test%false%25/08/2021 1400",
            new Deadline("This is a test", "25/08/2021 1400").toStorageFormat());
        assertEquals("D%This is a test%true%25/08/2021 1400",
            new Deadline("This is a test", true, "25/08/2021 1400").toStorageFormat());
    }

    @Test
    public void deadlineCreation_missingTime_exceptionThrown() {
        Assertions.assertThrows(DateTimeParseException.class, () -> {
            new Deadline("Missing time", "25/08/2021");
        });
    }
}
