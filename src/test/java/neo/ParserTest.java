package neo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 *  This class tests the class Parser.
 *  The class Parser makes sense of the user inputs.
 *
 * @author Ryan Tian Jun.
 */
public class ParserTest {

    @Test
    public void getCommandType_returnCommandType_returnEnum() {
        try {
            Parser parser = new Parser("todo testing");
            assertEquals(Parser.Command.TODO, parser.getCommandType());
        } catch (NeoException e) {
            fail();
        }
    }

    @Test
    public void getTaskNumber_returnTaskNumber_returnNumber() {
        try {
            Ui ui = new Ui("todo testing");
            Parser parser = new Parser("done 1");
            assertEquals(1, parser.getTaskNumber());
        } catch (NeoException e) {
            fail();
        }
    }

}
