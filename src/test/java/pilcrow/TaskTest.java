package pilcrow;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    Task task1 = Task.createTask("todo", "eat breakfast", true);
    Task task2 = Task.createTask("deadline", "finish iP/2021-08-25", false);

    @Test
    public void createTask_validInputs_success() {
        assertEquals(task1.toString(), "[T] [X] eat breakfast");
        assertEquals(task2.toString(), "[D] [ ] finish iP (25 Aug 2021)");
    }

    @Test
    public void convertToStoredTask_validInputs_success() {
        assertEquals(task1.convertToStoredTask(), "todo Teat breakfast");
        assertEquals(task2.convertToStoredTask(), "deadline Ffinish iP/2021-08-25");
    }
}
