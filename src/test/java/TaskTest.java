import whobot.main.WhoBotException;
import whobot.task.Deadline;
import whobot.task.Event;
import whobot.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    @Test
    public void TestTodo() {
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

    @Test
    public void TestDeadline() {
        try {
            Deadline sample1 = new Deadline("Sample Deadline /by 28/09/2021");
            Deadline sample2 = new Deadline("Sample Deadline /by 28/09/2021 10:00");

            assertEquals("[D] [ ] Sample Deadline (by: Sep 28 2021)", sample1.toString(), "toString() Function Error");
            assertEquals("[D] [ ] Sample Deadline (by: Sep 28 2021 10:00 AM)", sample2.toString(), "toString() Function Error");

            sample2.markAsDone();
            assertEquals("[D] [X] Sample Deadline (by: Sep 28 2021 10:00 AM)", sample2.toString(), "markAsDone() Function Error");

            assertNotEquals(sample2, sample1, "equals() Function Error");

            assertEquals("D", sample1.getType(), "getType() Function Error");
        } catch (WhoBotException e) {
            fail("Deadline Constructor Error");
        }
    }

    @Test
    public void TestEvent() {
        try {
            Event sample1 = new Event("Sample Event /at 28/10/2021 16:00");
            Event sample2 = new Event("Sample Event /at 28/09/2021 10:00");

            assertEquals("[E] [ ] Sample Event (at: Oct 28 2021 04:00 PM)", sample1.toString(), "toString() Function Error");
            assertEquals("[E] [ ] Sample Event (at: Sep 28 2021 10:00 AM)", sample2.toString(), "toString() Function Error");

            sample2.markAsDone();
            assertEquals("[E] [X] Sample Event (at: Sep 28 2021 10:00 AM)", sample2.toString(), "markAsDone() Function Error");

            assertNotEquals(sample2, sample1, "equals() Function Error");

            assertEquals("E", sample1.getType(), "getType() Function Error");
        } catch (WhoBotException e) {
            fail("Event Constructor Error");
        }
    }
}
