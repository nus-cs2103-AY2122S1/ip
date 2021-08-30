package duke.commands;

import org.junit.Assert;
import org.junit.Test;

public class UnknownCommandTest {
    @Test
    public void testExit() {
        UnknownCommand uk = new UnknownCommand();
        Assert.assertFalse(uk.isExit());
    }
}
