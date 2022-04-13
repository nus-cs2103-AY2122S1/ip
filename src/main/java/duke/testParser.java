package duke;

//import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.fail;


public class testParser {
    @Test
    public void parser_validInput_success() throws Exception{
        assertEquals(false, Parser.parser("todo borrow book", new TaskList()));
        assertEquals(false, Parser.parser("event attend lecture/at 2021-12-12", new TaskList()));
    }

    @Test
    public void parser_invalidInput_exceptionThrown() {
        try{
            assertEquals(false, Parser.parser("todo", new TaskList()));
            fail();
        }
        catch(DukeException e) {
            new Exception("invalid input");
        }
    }
}
