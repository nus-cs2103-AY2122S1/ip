import lebron.task.Deadline;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeadlineTest {

    @Test
    void toFile() {
        Deadline deadline = new Deadline("work", "2021-08-30");
        assertEquals("D | 0 | work | 2021-08-30", deadline.toFile());
    }
}