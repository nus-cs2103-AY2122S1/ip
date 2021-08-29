package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.command.Operation;
import duke.exception.DukeException;

public class ParserTest {
    @Test
    public void checkResponseTest() {
        Operation op = Operation.DELETE;
        try {
            op = Parser.checkResponse("todo ggg", 8);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        assertEquals(Operation.TODO, op);
    }

    @Test
    public void checkDigitTest() {
        boolean flag = Parser.chekDigit("-1154");
        assertTrue(flag);
    }
}
