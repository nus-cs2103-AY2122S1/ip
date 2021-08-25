package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testStringConversion() {
        assertEquals("[E]|[]|buy food|2011-01-01",
                new Event("buy food", "2011-01-01").toString());
    }
}
