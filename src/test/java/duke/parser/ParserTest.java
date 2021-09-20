package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

public class ParserTest {
    @Test
    public void testGetOperationType() {
        try {
            assertEquals("todo", new Parser("todo borrow book").getOperationType());
            assertEquals("deadline", new Parser("deadline return book /by 2019-09-28").getOperationType());
            assertEquals("done", new Parser("done 1").getOperationType());
            assertEquals("bye", new Parser("bye").getOperationType());
            assertEquals("delete", new Parser("delete 1").getOperationType());
            assertEquals("list", new Parser("list").getOperationType());
        } catch (DukeException e) {
            fail();
        }
    }


}
