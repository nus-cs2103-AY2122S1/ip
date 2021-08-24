package duke;

import org.junit.Assert;
import org.junit.Test;

/**
 * A class to test the methods in <code>Task</code>.
 */
public class TaskTest {
    @Test
    public void testGetStatusIcon(){
        Task task = new Task("description", true);
        String status = task.getStatusIcon();
        Assert.assertEquals("X", status);
    }
}
