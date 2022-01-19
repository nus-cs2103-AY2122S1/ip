package duchess.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import duchess.main.DuchessException;

/**
 * This class implements a JUnit Test for the Deadline class' methods.
 *
 * @author Amos Tan
 * @version CS2103T AY21/22 Semester 1
 */
public class DeadlineTest {
    /**
     * Tests the toFileFormat() method.
     */
    @Test
    public void testToFileFormat() {
        Deadline d = new Deadline("homework",
                LocalDateTime.of(2021, 12, 25, 12, 00));
        assertEquals(d.toFileFormat(), "Dhomework,Dec 25 2021 12:00,false");
        d.setDoneStatus(true);
        assertEquals(d.toFileFormat(), "Dhomework,Dec 25 2021 12:00,true");
    }

    /**
     * Tests the convertStringToDate() method with valid input.
     * @throws DuchessException When an incorrect format is used for Deadline.
     */
    @Test
    public void testConvertStringToDate_validDate_success() throws DuchessException {
        assertEquals(Deadline.convertStringToDate("25/12/2021 12pm"),
                LocalDateTime.of(2021, 12, 25, 12, 00));
    }

    /**
     * Tests the convertStringToDate() method with invalid input.
     * @throws DuchessException When an incorrect format is used for Deadline.
     */
    @Test
    public void testConvertStringToDate_invalidDate_exceptionThrown() {
        try {
            assertEquals(Deadline.convertStringToDate("25/13/2021 12pm"),
                    LocalDateTime.of(2021, 13, 25, 12, 00));
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
        assertEquals(Deadline.convertTextToDate("Dec 25 2021 12:00"),
                LocalDateTime.of(2021, 12, 25, 12, 00));
        assertEquals(Deadline.convertTextToDate("Jan 4 2022 13:26"),
                LocalDateTime.of(2022, 1, 4, 13, 26));
    }
}
