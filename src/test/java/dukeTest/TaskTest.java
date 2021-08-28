package dukeTest;

import duke.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void toStringTest() {
        Task task = new Task.Deadline("Clean room", false, "2021-12-12");
        assertEquals("deadline | 0 | Clean room | 2021-12-12", task.toString());
    }
}
