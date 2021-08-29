package seedu.duke;

import org.junit.jupiter.api.Test;

import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DateTimeManagerTest {

    @Test
    public void parseDate_invalidFormat_exceptionThrown() {
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String invalidDateFormat = "23-3-2001";
        DateTimeManager manager = new DateTimeManager(customFormatter);
        Exception e = assertThrows(
                DukeException.class, () -> manager.parseDateTime(invalidDateFormat));

        String expectedMessage = "Cannot read date.";
        String actualMessage = e.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void parseDate_validFormat_localDate() {
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String validDateFormat = "23/03/2001";
        DateTimeManager manager = new DateTimeManager(customFormatter);

        assertDoesNotThrow(() -> manager.parseDateTime(validDateFormat));
    }

}
