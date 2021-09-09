package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class TaskTest {
    /**
     * Checks that task deserialization matches expectation
     */
    @Test
    public void deserialize_correct() {
        Task task = Task.deserialize("Deadline,false,return book,Dec 02 2019 - 06 00 PM");
        assertTrue(task instanceof DeadlineTask);

        assertTrue(!task.isTaskDone());
        assertEquals("return book", task.getTaskText());
        assertEquals("Dec 02 2019 - 06 00 PM", task.getTaskTime());
    }

    /**
     * Checks that task serialization matches expectation
     */
    @Test
    public void serialize_correct() {
        LocalDateTime taskTime = LocalDateTime.parse("25/08/2021 1200", Task.INPUT_TIME_FORMAT);
        Task task = new DeadlineTask("Get help", taskTime);
        task.markDone();
        assertEquals("Deadline,true,Get help,Aug 25 2021 - 12 00 PM", task.serialize());
    }
}
