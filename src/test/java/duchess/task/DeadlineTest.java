package duchess.task;

import duchess.main.DuchessException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTest {
    @Test
    public void testFileFormat() {
        Deadline d = new Deadline("homework",
                LocalDateTime.of(2021, 12, 25, 12, 00));
        assertEquals(d.toFileFormat(), "Dhomework,Dec 25 2021 12:00,false");
        d.setDone(true);
        assertEquals(d.toFileFormat(), "Dhomework,Dec 25 2021 12:00,true");
    }

    @Test
    public void testConvertStringToDate_validDate_success() throws DuchessException {
        assertEquals(Deadline.convertStringToDate("25/12/2021 12pm"),
                LocalDateTime.of(2021, 12, 25, 12, 00));
    }

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

    @Test
    public void testConvertTextToDate() {
        assertEquals(Deadline.convertTextToDate("Dec 25 2021 12:00"),
                LocalDateTime.of(2021, 12, 25, 12, 00));
        assertEquals(Deadline.convertTextToDate("Jan 4 2022 13:26"),
                LocalDateTime.of(2022, 1, 4, 13, 26));
    }
}
