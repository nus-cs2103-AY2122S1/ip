package duke;

import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void invalidCommandReturnsNull() {
        assertNull(new Parser().parse("garbage"));
    }
}
