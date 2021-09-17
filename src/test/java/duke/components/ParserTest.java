package duke.components;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void testParserIsMarkDoneCommand() {
        Parser parser = new Parser();
        assertEquals(true, parser.isMarkDoneCommand("done 1"));
    }
}
