package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.exceptions.UserInputError;
import duke.tasks.Event;



public class EventTest {
    @Test
    public void testOutput1() throws UserInputError {
        Event event = new Event("merge branch", "2021-09-09 0900-1200", true);
        assertEquals("[E][X] merge branch { at: Sep 9 2021 || 0900 - 1200 }", event.toString());
    }

    @Test
    public void testOutput2() throws UserInputError {
        Event event = new Event("merge branch", "2021-09-09 0930-2359", false);
        assertEquals("[E][  ] merge branch { at: Sep 9 2021 || 0930 - 2359 }", event.toString());
    }

    @Test
    public void testInvalidDate1() {
        assertThrows(UserInputError.class, () ->
                new Event("merge branch", "2021-90-09 1299-2359", false));
    }

    @Test
    public void testInvalidDate2() {
        assertThrows(UserInputError.class, () ->
                new Event("merge branch", "next", false));
    }

    @Test
    public void testTaskToString1() throws UserInputError {
        Event event = new Event("merge master", "2021-09-09 0930-1200", false);
        assertEquals("E ~#~ 0 ~#~ merge master ~#~ 2021-09-09 0930 - 1200", event.convertTaskToString());
    }
}
