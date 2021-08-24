package test;

import duke.Duke;
import duke.Parser;
import org.junit.Assert;
import org.junit.Test;

/**
 * A class to test the methods in <code>Parser</code>.
 */
public class ParserTest {

    @Test
    public void testIfValidDateTrue() {
        Parser parser = new Parser(new Duke("src/main/java/data/list.txt"));
        boolean isValidDate = parser.isValid("2019-01-01");
        Assert.assertTrue(isValidDate);
    }
}
