package duke.commands;

import org.junit.Assert;
import org.junit.Test;

public class ExitCommandTest {

    @Test
    public void testExit() {
        ExitCommand exit = new ExitCommand();
        Assert.assertTrue(exit.isExit());
    }
}
