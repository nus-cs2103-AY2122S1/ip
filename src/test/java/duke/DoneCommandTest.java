package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.command.DoneCommand;

public class DoneCommandTest {
    @Test
    public void doneCommand_exit_success() {
        assertEquals(false,
                new DoneCommand("done 1").isExit());
    }
}
