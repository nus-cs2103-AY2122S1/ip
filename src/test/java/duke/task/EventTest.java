package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class EventTest {
    public static final LocalDate LOCALDATE = LocalDate.parse("2021-01-01");
    public static final String LOCAL_DATE_STRING = LOCALDATE.format(DateTimeFormatter.ofPattern("MMM d yyyy"));

    @Test
    public void constructorWithEmptyDescriptionTest() {
        try {
            new Event("", null);
            fail();
        } catch (Exception e) {
            assertEquals("Your description cannot be empty!", e.getMessage());
        }

        try {
            new Event("", true, null);
            fail();
        } catch (Exception e) {
            assertEquals("Your description cannot be empty!", e.getMessage());
        }
    }

    @Test
    public void getDate() {
        assertEquals(LOCALDATE, new Event("project", LOCALDATE).getDate());
        assertEquals(LOCALDATE,
                new Event("project", true, LOCALDATE).getDate());
    }

    @Test
    public void taskToLineTest() {
        assertEquals("E | 0 | project | " + LOCALDATE,
                new Event("project", LOCALDATE).taskToLine());
        assertEquals("E | 1 | project | " + LOCALDATE,
                new Event("project", true, LOCALDATE).taskToLine());
    }

    @Test
    public void toStringTest() {
        assertEquals("[E][ ] project (at: " + LOCAL_DATE_STRING + ")",
                new Event("project", LOCALDATE).toString());
        assertEquals("[E][X] project (at: " + LOCAL_DATE_STRING + ")",
                new Event("project", true, LOCALDATE).toString());
    }
}
