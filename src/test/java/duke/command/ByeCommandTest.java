package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ByeCommandTest {

    private Command c = new ByeCommand();

    @Test
    public void isExit_byeCommandInitialized_trueReturned() {
        assertEquals(c.isExit(), true);
    }
}
