import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


class ParserTest {
    @Test
    void parseCommand() {
        Blitz b = new Blitz("data/blitz.txt");
        boolean isEqual = "OOPS!!! Sorry, but I don't know what that means :-("
                .equals(Parser.parseCommand("hello", b.getTasks(), b.getUi()));
        assertEquals(true, isEqual);
    }
}
