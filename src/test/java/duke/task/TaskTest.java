package duke.task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void testTaskSetDone() {
        Task task = new Task("taskTest");
        task.setDone();
        assertEquals("[X] taskTest", task.toString());
    }

}
