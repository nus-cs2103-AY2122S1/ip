import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskManagerTest {

    @Test
    public void taskManagerTest() {
        TaskManager taskManager = new TaskManager();
        assertEquals("Bye. Hope to see you again soon!", taskManager.executeCommand("bye"));
    }
}
