import duke.Deadline;
import duke.TaskList;
import duke.ToDo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void getTaskTypeTest() {
        Deadline deadline = new Deadline("", "2021-10-10");
        assertEquals('D', deadline.getTaskType());
    }

    @Test
    public void getTimeTest() {
        Deadline deadline = new Deadline("", "2021-10-10");
        assertEquals("2021-10-10", deadline.getTime());
    }
}
