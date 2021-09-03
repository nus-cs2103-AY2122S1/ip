package duke;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CommandTest {
    @Test
    public void isExitTest_commandBye_true() {
        assertTrue(new Command(Command.Commands.BYE, "").isExit());
    }

    @Test
    public void isExitTest_commandDeadline_false() {
        assertFalse(new Command(Command.Commands.DEADLINE, "").isExit());
    }

    @Test
    public void isExitTest_commandDelete_false() {
        assertFalse(new Command(Command.Commands.DELETE, "").isExit());
    }

    @Test
    public void isExitTest_commandDone_false() {
        assertFalse(new Command(Command.Commands.DONE, "").isExit());
    }

    @Test
    public void isExitTest_commandEvent_false() {
        assertFalse(new Command(Command.Commands.EVENT, "").isExit());
    }

    @Test
    public void isExitTest_commandList_false() {
        assertFalse(new Command(Command.Commands.LIST, "").isExit());
    }

    @Test
    public void isExitTest_commandTodo_false() {
        assertFalse(new Command(Command.Commands.TODO, "").isExit());
    }

    @Test
    public void isExitTest_commandUnknown_false() {
        assertFalse(new Command(Command.Commands.UNKNOWN, "").isExit());
    }
}
