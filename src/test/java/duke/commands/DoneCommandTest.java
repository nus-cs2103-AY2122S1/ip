package duke.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

class DoneCommandTest {

    @Test
    void testIsExit() {
        assertFalse(new DoneCommand("done 1").isExit());
    }
}
