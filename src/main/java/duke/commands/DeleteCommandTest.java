package duke.commands;

import org.junit.Assert;
import org.junit.Test;

public class DeleteCommandTest {

    @Test
    public void testDelete() {
        int index = 5;
        DeleteCommand del = new DeleteCommand(index);
        Assert.assertEquals(del.getDeletionIndex(), 5);
    }

    @Test
    public void testExit() {
        DeleteCommand del = new DeleteCommand(5);
        Assert.assertFalse(del.isExit());
    }
}
