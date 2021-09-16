import org.junit.jupiter.api.Test;
import duke.Parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parseStringFromCommands(){
        String instruction = Parser.getStringFrom("deadline", "deadline Return book /by: 2021-11-12");
        assertEquals("Return book /by: 2021-11-12", instruction);
    }

    @Test
    public void parseIntFromCommands(){
        int instruction = Parser.getIntFrom("done", "done 123");
        assertEquals(123, instruction);
    }
}
