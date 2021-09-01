package duke.exceptions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeExceptionTest {
    @Test
    public void getMessageTest() {
        DukeException de = new DukeException("Error occurred");
        assertEquals("Error occurred", de.getMessage());
    }
}
