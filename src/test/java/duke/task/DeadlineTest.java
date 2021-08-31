package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    public static final LocalDate LOCALDATE = LocalDate.parse("2021-01-01");
    public static final String LOCAL_DATE_STRING = LOCALDATE.format(DateTimeFormatter.ofPattern("MMM d yyyy"));

    @Test
    public void constructorWithEmptyDescriptionTest() {
        try {
            new Deadline("", null);
            fail();
        } catch (Exception e) {
            assertEquals("Your description cannot be empty!", e.getMessage());
        }

        try {
            new Deadline("", true, null);
            fail();
        } catch (Exception e) {
            assertEquals("Your description cannot be empty!", e.getMessage());
        }
    }

    @Test
    public void getDate() {
        assertEquals(LOCALDATE, new Deadline("project", LOCALDATE).getDate());
        assertEquals(LOCALDATE,
                new Deadline("project", true, LOCALDATE).getDate());
    }

    @Test
    public void taskToLineTest() {
        assertEquals("D | 0 | project | " + LOCALDATE,
                new Deadline("project", LOCALDATE).taskToLine());
        assertEquals("D | 1 | project | " + LOCALDATE,
                new Deadline("project", true, LOCALDATE).taskToLine());
    }

    @Test
    public void toStringTest() {
        assertEquals("[D][ ] project (by: " + LOCAL_DATE_STRING + ")",
                new Deadline("project", LOCALDATE).toString());
        assertEquals("[D][X] project (by: " + LOCAL_DATE_STRING + ")",
                new Deadline("project", true, LOCALDATE).toString());
    }
}
