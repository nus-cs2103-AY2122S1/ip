package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.text.ParseException;

import org.junit.jupiter.api.Test;

public class DeadlinesTest {
    @Test
    public void toString_parseableDate_success() {
        try {
            Deadlines deadline = new Deadlines("CS2103", "21/8/21 1500");
            assertEquals("[D][ ] CS2103 (by: 21/08/21 1500)", deadline.toString());
        } catch (ParseException e) {
            fail();
        }

        try {
            Deadlines deadline = new Deadlines("CS2103", "21/8/21 1500", true);
            assertEquals("[D][X] CS2103 (by: 21/08/21 1500)", deadline.toString());
        } catch (ParseException e) {
            fail();
        }

        try {
            Deadlines deadline = new Deadlines("CS2103", "21/8/21 1500", false);
            assertEquals("[D][ ] CS2103 (by: 21/08/21 1500)", deadline.toString());
        } catch (ParseException e) {
            fail();
        }

    }

    @Test
    public void toString_unparseableDate_exceptionThrown() {
        try {
            Deadlines deadline = new Deadlines("CS2103", "21/8/21 p");
            fail();
        } catch (ParseException e) {
            assertEquals("Unparseable date: \"21/8/21 p\"", e.getMessage());
        }

        try {
            Deadlines deadline = new Deadlines("CS2103", "21/8/21 p", true);
            fail();
        } catch (ParseException e) {
            assertEquals("Unparseable date: \"21/8/21 p\"", e.getMessage());
        }
    }

    @Test
    public void testSaveOutputConversion() {
        try {
            Deadlines deadline = new Deadlines("CS2103", "21/8/21 1500");
            assertEquals("D | CS2103 | 0 | 21/08/21 1500", deadline.saveOutput());
        } catch (ParseException e) {
            fail();
        }

        try {
            Deadlines deadline = new Deadlines("CS2103", "21/8/21 1500", true);
            assertEquals("D | CS2103 | 1 | 21/08/21 1500", deadline.saveOutput());
        } catch (ParseException e) {
            fail();
        }

        try {
            Deadlines deadline = new Deadlines("CS2103", "21/8/21 1500", false);
            assertEquals("D | CS2103 | 0 | 21/08/21 1500", deadline.saveOutput());
        } catch (ParseException e) {
            fail();
        }
    }
}
