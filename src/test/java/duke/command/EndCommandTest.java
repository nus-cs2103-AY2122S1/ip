package duke.command;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

class EndCommandTest {

    @Test
    void isRunning() {
        EndCommand end = new EndCommand();
        assertFalse(end.isRunning());
    }
}
