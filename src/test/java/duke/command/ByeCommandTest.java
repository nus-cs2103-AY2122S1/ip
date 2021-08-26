package duke.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ByeCommandTest {

    @Test
    public void testExecute() {
        boolean isExit = false;
        ByeCommand byeCommand = new ByeCommand();
        isExit = byeCommand.isExit;
        assertEquals(true, isExit);
    }
}