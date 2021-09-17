package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


class ByeCommandTest {

    @Test
    public void testExecute() {
        boolean isExit = false;
        ByeCommand byeCommand = new ByeCommand();
        isExit = byeCommand.isExit;
        assertEquals(true, isExit);
    }
}
