package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testStringConversion() {
        Deadline dummyDeadline = new Deadline("Dummy deadline", "2020-10-10 6pm");
        assertEquals("[D][ ] Dummy deadline (by: Oct 10 2020 6pm)", dummyDeadline.toString());
        dummyDeadline.setCompleted();
        assertEquals("[D][X] Dummy deadline (by: Oct 10 2020 6pm)", dummyDeadline.toString());
    }

    @Test
    public void testStorageFormatConversion() {
        Deadline dummyDeadline = new Deadline("Dummy deadline", "2020-10-10 6pm");
        assertEquals("D/0/Dummy deadline/2020-10-10 6pm", dummyDeadline.toStorageFormat());
        dummyDeadline.setCompleted();
        assertEquals("D/1/Dummy deadline/2020-10-10 6pm", dummyDeadline.toStorageFormat());
    }
}
