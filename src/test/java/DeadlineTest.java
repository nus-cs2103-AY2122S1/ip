import org.junit.jupiter.api.Test;
import task.Deadline;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void getTask_newTask_success() {
        assertEquals(" hw (by Oct 15 2019)",
                new Deadline("deadline hw /by 2019-10-15").getTask());
    }
}
