package duke;

import org.junit.Assert;
import org.junit.Test;

public class DeadlineTest {
    @Test 
    public void testDeadlineToString(){
        Deadline deadline = new Deadline("description", "tomorrow", false, false);
        Assert.assertEquals("[D][ ] description (by: tomorrow)", deadline.toString());
    }
}
