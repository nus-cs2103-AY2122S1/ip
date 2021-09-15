import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import whobot.main.WhoBotException;
import whobot.task.Deadline;
import whobot.task.Event;
import whobot.task.Todo;



public class TaskTest {

    /***
     * Tests the Todo Class
     */
    @Test
    public void testTodo() {
        Todo sample1 = new Todo("Sample Task");
        Todo sample2 = new Todo("Sample Task");

        assertEquals("[T] [ ] Sample Task", sample1.toString(), "toString() Function Error");

        sample2.markAsDone();
        assertEquals("[T] [X] Sample Task", sample2.toString(), "markAsDone() Function Error");

        assertNotEquals(sample2, sample1, "equals() Function Error");

        sample1.markAsDone();
        assertEquals(sample2, sample1, "equals() Function Error");

        assertEquals("T", sample1.getType(), "getType() Function Error");
    }

    /***
     * Tests the Deadline Class
     */
    @Test
    public void testDeadline() {
        try {
            Deadline sample1 = new Deadline("Sample Deadline /by 28/09/2021");
            Deadline sample2 = new Deadline("Sample Deadline /by 28/09/2021 10:00");

            assertEquals("[D] [ ] Sample Deadline (by: Sep 28 2021)", sample1.toString(),
                    "toString() Function Error");
            assertEquals("[D] [ ] Sample Deadline (by: Sep 28 2021 10:00 AM)", sample2.toString(),
                    "toString() Function Error");

            sample2.markAsDone();
            assertEquals("[D] [X] Sample Deadline (by: Sep 28 2021 10:00 AM)",
                    sample2.toString(), "markAsDone() Function Error");

            assertNotEquals(sample2, sample1, "equals() Function Error");

            assertEquals("D", sample1.getType(), "getType() Function Error");
        } catch (WhoBotException e) {
            fail("Deadline Constructor Error");
        }
    }

    /***
     * Tests the Event Class
     */
    @Test
    public void testEvent() {
        try {
            Event sample1 = new Event("Sample Event /at 28/10/2021 16:00");
            Event sample2 = new Event("Sample Event /at 28/09/2021 10:00");

            assertEquals("[E] [ ] Sample Event (at: Oct 28 2021 04:00 PM)",
                    sample1.toString(), "toString() Function Error");
            assertEquals("[E] [ ] Sample Event (at: Sep 28 2021 10:00 AM)",
                    sample2.toString(), "toString() Function Error");

            sample2.markAsDone();
            assertEquals("[E] [X] Sample Event (at: Sep 28 2021 10:00 AM)",
                    sample2.toString(), "markAsDone() Function Error");

            assertNotEquals(sample2, sample1, "equals() Function Error");

            assertEquals("E", sample1.getType(), "getType() Function Error");
        } catch (WhoBotException e) {
            fail("Event Constructor Error");
        }
    }
}
