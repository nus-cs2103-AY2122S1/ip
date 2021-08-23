package duke;

import org.junit.Assert;
import org.junit.Test;

public class TaskTest {
    @Test
    public void testGetStatusIcon(){
        Task task = new Task("description", true);
        String status = task.getStatusIcon();
        Assert.assertEquals("X", status);
    }
}
