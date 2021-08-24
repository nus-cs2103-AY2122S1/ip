package duke.commands;

import duke.exceptions.InvalidTimeStampException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class AddDeadlineCommandTest {

    @Test
    public void testInvalidYear() {
        assertThrows(
                InvalidTimeStampException.class,
                () -> new AddDeadlineCommand(new String[] {"PLACEHOLDER_EVENT_DETAILS", "1/2/21 2021"})
        );
    }

    @Test
    public void testInvalidMonth() {
        assertThrows(
                InvalidTimeStampException.class,
                () -> new AddDeadlineCommand(new String[] {"PLACEHOLDER_EVENT_DETAILS", "1/13/2021 2021"})
        );
    }

    @Test
    public void testInvalidDay() {
        assertThrows(
                InvalidTimeStampException.class,
                () -> new AddDeadlineCommand(new String[] {"PLACEHOLDER_EVENT_DETAILS", "32/4/2021 2021"})
        );
    }

    @Test
    public void testInvalidHour() {
        assertThrows(
                InvalidTimeStampException.class,
                () -> new AddDeadlineCommand(new String[] {"PLACEHOLDER_EVENT_DETAILS", "32/4/2021 2421"})
        );
    }

    @Test
    public void testInvalidMinute() {
        assertThrows(
                InvalidTimeStampException.class,
                () -> new AddDeadlineCommand(new String[] {"PLACEHOLDER_EVENT_DETAILS", "32/4/2021 2460"})
        );
    }
}
