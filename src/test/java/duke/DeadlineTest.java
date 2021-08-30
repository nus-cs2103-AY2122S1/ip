package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
            Deadline deadline = new Deadline("completed deadline", "2012-12-29");
            deadline.setCompleted();
            assertEquals("[D][X] completed deadline (by: Dec 29 2012)", deadline.toString());
        } catch (DukeException e){
            System.out.println(e.toString());
        }
    }

    /**
     * Checks if incomplete deadlines are handled appropriately.
     */
    @Test
    public void incompleteDeadlineTest() {
        try {
            Deadline deadline = new Deadline("incomplete deadline", "2015-03-12");
            assertEquals("[D][ ] incomplete deadline (by: Mar 12 2015)", deadline.toString());
        } catch (DukeException e){
            System.out.println(e.toString());
        }
    }
}
