import duke.DukeException;
import org.junit.jupiter.api.Test;

import static duke.Parser.parse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void deleteWithoutNumberCommandTest() {
        Exception exception = assertThrows(DukeException.class, () -> parse("delete"));
        String expectedMessage = "☹ OOPS!!! The delete command needs a number after it in the following format: delete number";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);

    }
}
