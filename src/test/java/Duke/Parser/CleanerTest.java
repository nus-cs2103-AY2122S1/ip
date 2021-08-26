package Duke.Parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CleanerTest {
    @Test
    public void cleanerClean_invalidListCommand_errorMessage() {
        Cleaner cl = new Cleaner();
        assertEquals("error 1", cl.clean("list 2", 1));
        assertEquals("error 1", cl.clean("list 9oe9", 1));
        assertEquals("error -1", cl.clean("lists", 1));
    }
}
