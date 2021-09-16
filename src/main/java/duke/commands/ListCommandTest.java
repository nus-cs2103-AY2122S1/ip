package duke.commands;

import org.junit.Assert;
import org.junit.Test;

public class ListCommandTest {

    @Test
    public void testExit() {
        ListCommand lst = new ListCommand();
        Assert.assertFalse(lst.isExit());
    }

}
