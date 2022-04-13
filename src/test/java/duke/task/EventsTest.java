package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.text.ParseException;

import org.junit.jupiter.api.Test;


public class EventsTest {
    @Test
    public void toString_parseableDate_success() {
        try {
            Events event = new Events("CS2103", "21/8/21 1500");
            assertEquals("[E][ ] CS2103 (at: 21/08/21 1500)", event.toString());
        } catch (ParseException e) {
            fail();
        }

        try {
            Events event = new Events("CS2103", "21/8/21 1500", true);
            assertEquals("[E][X] CS2103 (at: 21/08/21 1500)", event.toString());
        } catch (ParseException e) {
            fail();
        }

        try {
            Events event = new Events("CS2103", "21/8/21 1500", false);
            assertEquals("[E][ ] CS2103 (at: 21/08/21 1500)", event.toString());
        } catch (ParseException e) {
            fail();
        }
    }


    @Test
    public void toString_unparseableDate_exceptionThrown() {
        try {
            Events event = new Events("CS2103", "21/8/21 p");
            fail();
        } catch (ParseException e) {
            assertEquals("Unparseable date: \"21/8/21 p\"", e.getMessage());
        }

        try {
            Events event = new Events("CS2103", "21/8/21 p", true);
            fail();
        } catch (ParseException e) {
            assertEquals("Unparseable date: \"21/8/21 p\"", e.getMessage());
        }
    }

    @Test
    public void testSaveOutputConversion() {
        try {
            Events event = new Events("CS2103", "21/8/21 1500");
            assertEquals("E | CS2103 | 0 | 21/08/21 1500", event.saveOutput());
        } catch (ParseException e) {
            fail();
        }

        try {
            Events event = new Events("CS2103", "21/8/21 1500", true);
            assertEquals("E | CS2103 | 1 | 21/08/21 1500", event.saveOutput());
        } catch (ParseException e) {
            fail();
        }

        try {
            Events event = new Events("CS2103", "21/8/21 1500", false);
            assertEquals("E | CS2103 | 0 | 21/08/21 1500", event.saveOutput());
        } catch (ParseException e) {
            fail();
        }
    }
}
