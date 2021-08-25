package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testStringConversion() {
        assertEquals("[D]|[]|buy food|2011-01-01",
                new Deadline("buy food", "2011-01-01").toString());
    }
}
