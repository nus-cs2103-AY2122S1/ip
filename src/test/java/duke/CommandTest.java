package duke;
import org.junit.Test;
import org.junit.Assert;

public class CommandTest {
    @Test
    public void isExitTest_commandBye_true() {
        Assert.assertTrue(new Command(Command.Commands.BYE, "").isExit());
    }

    @Test
    public void isExitTest_commandDeadline_false() {
        Assert.assertFalse(new Command(Command.Commands.DEADLINE, "").isExit());
    }

    @Test
    public void isExitTest_commandDelete_false() {
        Assert.assertFalse(new Command(Command.Commands.DELETE, "").isExit());
    }

    @Test
    public void isExitTest_commandDone_false() {
        Assert.assertFalse(new Command(Command.Commands.DONE, "").isExit());
    }

    @Test
    public void isExitTest_commandEvent_false() {
        Assert.assertFalse(new Command(Command.Commands.EVENT, "").isExit());
    }

    @Test
    public void isExitTest_commandList_false() {
        Assert.assertFalse(new Command(Command.Commands.LIST, "").isExit());
    }

    @Test
    public void isExitTest_commandTodo_false() {
        Assert.assertFalse(new Command(Command.Commands.TODO, "").isExit());
    }

    @Test
    public void isExitTest_commandUnknown_false() {
        Assert.assertFalse(new Command(Command.Commands.UNKNOWN, "").isExit());
    }
}