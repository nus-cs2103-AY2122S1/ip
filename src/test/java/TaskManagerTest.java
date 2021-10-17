import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskManagerTest {

    @Test
    public void taskListTest() {
        TaskList taskList = new TaskList();
        Storage storage = new Storage();
        Parser parser = new Parser();

        assertEquals("Bye. Hope to see you again soon!", parser.parse("bye", taskList, storage));
    }
}
