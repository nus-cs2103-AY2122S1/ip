import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.Duke;
import duke.Parser;

/**
 * A class to test the methods in <code>Parser</code>.
 */
public class ParserTest {

    @Test
    public void testIfValidDateTrue() {
        Parser parser = new Parser(new Duke());
        boolean isValidDate = parser.isValidLocalDate("2019-01-01");
        assertTrue(isValidDate);
    }
}
