package duchess.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import duchess.main.DuchessException;

/**
 * This class implements a JUnit Test for the Event class' methods.
 *
 * @author Amos Tan
 * @version CS2103T AY21/22 Semester 1
 */
public class EventTest {
    /**
     * Tests the toFileFormat() method.
     */
    @Test
    public void testToFileFormat() {
        Event e = new Event("homework",
                LocalDateTime.of(2021, 12, 25, 12, 00),
                LocalDateTime.of(2021, 12, 25, 16, 00));
        assertEquals(e.toFileFormat(), "Ehomework,Dec 25 2021 12:00,Dec 25 2021 16:00,false");
        e.setDoneStatus(true);
        assertEquals(e.toFileFormat(), "Ehomework,Dec 25 2021 12:00,Dec 25 2021 16:00,true");
    }

    /**
     * Tests the convertStringToDate() method with valid input.
     * @throws DuchessException When an incorrect format is used for Deadline.
     */
    @Test
    public void testConvertStringToDate_validDate_success() throws DuchessException {
        LocalDateTime[] eventConvertedDates = Event.convertStringToDates("25/12/2021", "12pm-4pm");
        LocalDateTime[] dates = { LocalDateTime.of(2021, 12, 25, 12, 00),
                LocalDateTime.of(2021, 12, 25, 16, 00)};
        assertEquals(dates[0], eventConvertedDates[0]);
        assertEquals(dates[1], eventConvertedDates[1]);
    }

    /**
     * Tests the convertStringToDate() method with invalid input.
     * @throws DuchessException When an incorrect format is used for Deadline.
     */
    @Test
    public void testConvertStringToDate_invalidDate_exceptionThrown() {
        try {
            LocalDateTime[] eventConvertedDates = Event.convertStringToDates("25/13/2021", "12pm-4pm");
            LocalDateTime[] dates = { LocalDateTime.of(2021, 13, 25, 12, 00),
                    LocalDateTime.of(2021, 13, 25, 16, 00)};
            assertEquals(dates[0], eventConvertedDates[0]);
            assertEquals(dates[1], eventConvertedDates[1]);
            fail();
        } catch (DuchessException e) {
            assertEquals("Wrong format used.", e.getMessage());
        }
    }

    /**
     * Tests the convertTextToDate() method.
     */
    @Test
    public void testConvertTextToDate() {
        assertEquals(Event.convertTextToDate("Dec 25 2021 12:00"),
                LocalDateTime.of(2021, 12, 25, 12, 00));
        assertEquals(Event.convertTextToDate("Jan 4 2022 13:26"),
                LocalDateTime.of(2022, 1, 4, 13, 26));
    }
}
