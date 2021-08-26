package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    public void noArgumentProvided_done_exceptionThrown() {
        try {
            assertEquals(3, Parser.validateDone("done"));
            fail();
        } catch (Exception e) {
            assertEquals("Please specify the task number.", e.getMessage());
        }
    }
}
