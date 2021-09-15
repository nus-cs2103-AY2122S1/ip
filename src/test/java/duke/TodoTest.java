package duke;

import duke.task.Task;
import duke.task.Todo;

import org.junit.Test;
import org.junit.Assert;

public class TodoTest {
    @Test
    public void getStatusIcon_todoObject_X() {
        Task task = new Todo("Watch football");
        task.markAsDone();
        Assert.assertEquals("X", task.getStatusIcon());
    }

    @Test
    public void taskListOnDisk_todoObject_success() {
        Task task = new Todo("Watch football");
        task.markAsDone();
        Assert.assertEquals("duke.task.Todo |X| Watch football", task.getTaskListOnDisk());
    }

    @Test
    public void toString_todoObject_success() {
        Task task = new Todo("Watch football");
        task.markAsDone();
        Assert.assertEquals("[T][X] Watch football", task.toString());
    }
}
