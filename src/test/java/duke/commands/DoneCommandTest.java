package duke.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoneCommandTest {

    @Test
    void isExit() {
        assertFalse(new DoneCommand("done 1").isExit());
    }
}