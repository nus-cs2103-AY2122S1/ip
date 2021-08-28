package duke.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EndCommandTest {

    @Test
    void isRunning() {
        EndCommand end = new EndCommand();
        assertFalse(end.isRunning());
    }
}