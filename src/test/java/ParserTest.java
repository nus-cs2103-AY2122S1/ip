import Wonderland.DukeException;
import Wonderland.Keyword;
import Wonderland.Parser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ParserTest {
    @Test
    public void parse_validInput() {
        Parser parser = new Parser();
        String input = "list";
        Keyword output;
        try {
            output = Parser.parse(input);
        } catch (DukeException e) {
            output = Keyword.TODO;
            e.toString();
        }
        assertEquals(Keyword.LIST, output);

    }
}
