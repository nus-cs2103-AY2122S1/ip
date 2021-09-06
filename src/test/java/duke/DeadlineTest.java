package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

/**
 * Class to test deadline objects.
 */
public class DeadlineTest {

    /**
     * Checks if completed deadlines are handled appropriately.
     */
    @Test
    public void completedDeadlineTest() {
        try {
            Deadline deadline = new Deadline("completed deadline", LocalDate.now());
            deadline.setCompleted();
            String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            assertEquals("[D][X] completed deadline (by: " + currentDate + ")", deadline.toString());
        } catch (DukeException e) {
            System.out.println(e);
        }
    }

    /**
     * Checks if incomplete deadlines are handled appropriately.
     */
    @Test
    public void incompleteDeadlineTest() {
        try {
            Deadline deadline = new Deadline("incomplete deadline", LocalDate.now());
            String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            assertEquals("[D][ ] incomplete deadline (by: " + currentDate + ")", deadline.toString());
        } catch (DukeException e) {
            System.out.println(e);
        }
    }
}
