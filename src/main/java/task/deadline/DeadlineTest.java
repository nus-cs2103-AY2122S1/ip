package task.deadline;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void getTask_newTask_success() {
        assertEquals(" party (by 2019-10-15)",
                new Deadline("deadline hw /by 2019-10-15").getTask());
    }
}
