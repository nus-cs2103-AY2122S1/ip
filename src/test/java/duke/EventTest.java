package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

/**
 * Class to test event objects.
 */
public class EventTest {

    /**
     * Checks if completed deadlines are handled appropriately.
     */
    @Test
    public void completedEventTest() {
        try {
            Event event = new Event("completed event", LocalDate.now());
            event.setCompleted();
            String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            assertEquals("[E][X] completed event (at: " + currentDate + ")", event.toString());
        } catch (DukeException e) {
            System.out.println(e);
        }
    }

    /**
     * Checks if incomplete deadlines are handled appropriately.
     */
    @Test
    public void incompleteEventTest() {
        try {
            Event event = new Event("incomplete event", LocalDate.now());
            String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            assertEquals("[E][ ] incomplete event (at: " + currentDate + ")", event.toString());
        } catch (DukeException e) {
            System.out.println(e);
        }
    }
}
