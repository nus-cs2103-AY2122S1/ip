package duke;

import duke.exceptions.UserInputError;
import duke.tasks.Event;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EventTest {
    @Test
    public void testOutput1() throws UserInputError {
        Event event = new Event("merge branch", "2021-09-09", true);
        assertEquals("[E][X] merge branch (at: Sep 9 2021)", event.toString());
    }

    @Test
    public void testOutput2() throws UserInputError {
        Event event = new Event("merge branch", "2021-09-09", false);
        assertEquals("[E][ ] merge branch (at: Sep 9 2021)", event.toString());
    }

    @Test
    public void testInvalidDate1() {
        assertThrows(UserInputError.class, () ->
                new Event("merge branch", "2021-90-09", false));
    }

    @Test
    public void testInvalidDate2() {
        assertThrows(UserInputError.class, () ->
                new Event("merge branch", "tmr", false));
    }

    @Test
    public void testTaskToString1() throws UserInputError {
        Event event = new Event("merge master", "2021-09-09", false);
        assertEquals("E ~#~ 0 ~#~ merge master ~#~ 2021-09-09", event.taskToString());
    }
}
