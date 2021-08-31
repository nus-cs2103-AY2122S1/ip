package duke.commands;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.exceptions.InvalidTimeStampException;

public class AddEventCommandTest {

    @Test
    public void testInvalidYear() {
        assertThrows(
            InvalidTimeStampException.class,
            () -> new AddEventCommand(new String[] {"PLACEHOLDER_EVENT_DETAILS", "1/2/21 2021"})
        );
    }

    @Test
    public void testInvalidMonth() {
        assertThrows(
            InvalidTimeStampException.class,
            () -> new AddEventCommand(new String[] {"PLACEHOLDER_EVENT_DETAILS", "1/13/2021 2021"})
        );
    }

    @Test
    public void testInvalidDay() {
        assertThrows(
            InvalidTimeStampException.class,
            () -> new AddEventCommand(new String[] {"PLACEHOLDER_EVENT_DETAILS", "32/4/2021 2021"})
        );
    }

    @Test
    public void testInvalidHour() {
        assertThrows(
            InvalidTimeStampException.class,
            () -> new AddEventCommand(new String[] {"PLACEHOLDER_EVENT_DETAILS", "32/4/2021 2421"})
        );
    }

    @Test
    public void testInvalidMinute() {
        assertThrows(
            InvalidTimeStampException.class,
            () -> new AddEventCommand(new String[] {"PLACEHOLDER_EVENT_DETAILS", "32/4/2021 2460"})
        );
    }
}
