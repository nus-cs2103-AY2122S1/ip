package duke;

import org.junit.Assert;
import org.junit.Test;

public class ParserTest {
    @Test
    public void testIfValidDateTrue(){
        Parser parser = new Parser(new Duke("src/main/java/data/list.txt"));
        boolean isValidDate = parser.isValid("2019-01-01");
        Assert.assertTrue(isValidDate);
    }
}
