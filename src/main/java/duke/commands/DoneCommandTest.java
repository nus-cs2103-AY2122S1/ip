package duke.commands;

import org.junit.Assert;
import org.junit.Test;

public class DoneCommandTest {

    @Test
    public void testDone() {
        int index = 5;
        DoneCommand done = new DoneCommand(index);
        Assert.assertEquals(done.getTaskIndex(), 5);
    }

    @Test
    public void testExit() {
        DoneCommand done = new DoneCommand(5);
        Assert.assertFalse(done.isExit());
    }
}
