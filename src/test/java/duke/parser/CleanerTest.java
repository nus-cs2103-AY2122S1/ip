package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CleanerTest {
    @Test
    public void cleanerClean_invalidListCommand_errorMessage() {
        Cleaner cl = new Cleaner();
        assertEquals("POLLUTED_LIST_COMMAND_EXCEPTION", cl.clean("list 2", 1));
        assertEquals("POLLUTED_LIST_COMMAND_EXCEPTION", cl.clean("list 9oe9", 1));
        assertEquals("INVALID_COMMAND_EXCEPTION", cl.clean("lists", 1));
    }
}
