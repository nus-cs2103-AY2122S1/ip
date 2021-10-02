import duke.tasks.Deadline;
import duke.tasks.Task;
import duke.tasks.TaskUtils;
import duke.tasks.Todo;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class TaskUtilsTest {
    @Test
    public void serializeTaskTest() {
        Todo t = new Todo("Die");
        String serialized = TaskUtils.taskToString(t);
        Assert.assertEquals(serialized, "T | 0 | Die");
        t.markDone();
        serialized = TaskUtils.taskToString(t);
        Assert.assertEquals(serialized, "T | 1 | Die");
    }

    @Test
    public void taskFromSerializationTest() {
        String string = "D | 0 | Live | 1999-02-10";
        Task task = new Deadline("Live", LocalDate.parse("1999-02-10"));
        Assert.assertEquals(task, TaskUtils.stringToTask(string));
    }
}
