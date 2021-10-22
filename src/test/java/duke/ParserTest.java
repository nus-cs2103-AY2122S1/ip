package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

public class ParserTest {
    @Test
    public void invalidCommandReturnsNull() {
        assertNull(new Parser().parse("garbage"));
    }
}
