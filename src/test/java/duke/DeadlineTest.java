package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void testStringConversion() {
        assertEquals("[D]|[]|buy food|2011-01-01",
                new Deadline("buy food", "2011-01-01").toString());
    }
}
