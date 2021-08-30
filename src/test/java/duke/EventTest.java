package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Class to test event objects.
 */
public class EventTest {

    /**
     * Checks if completed events are handled appropriately.
     */
    @Test
    public void completedEventTest() {
        try {
            Event event = new Event("completed event", "2012-12-29");
            event.setCompleted();
            assertEquals("[E][X] completed event (at: Dec 29 2012)", event.toString());
        } catch (DukeException e) {
            System.out.println(e);
        }
    }

    /**
     * Checks if incomplete events are handled appropriately.
     */
    @Test
    public void incompleteEventTest() {
        try {
            Event event = new Event("incomplete event", "2015-03-12");
            assertEquals("[E][ ] incomplete event (at: Mar 12 2015)", event.toString());
        } catch (DukeException e) {
            System.out.println(e);
        }
    }
}
