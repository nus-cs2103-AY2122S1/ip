package duke.components;
import duke.components.Parser;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void testParserIsMarkDoneCommand() {
        Parser parser = new Parser();
        assertEquals(true, parser.isMarkDoneCommand("done 1"));
    }
}
