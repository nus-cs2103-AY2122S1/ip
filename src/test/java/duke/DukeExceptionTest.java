package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DukeExceptionTest {
    @Test
    public void getMessageTest() {
        assertEquals("OOPS! I'm sorry, but I don't know that command",
                new DukeException("OOPS! I'm sorry, but I don't know that command").getMessage());
    }
}
