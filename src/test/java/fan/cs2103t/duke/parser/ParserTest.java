package fan.cs2103t.duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

class ParserTest {

    @Test
    public void parseCommand_wrongDeadlineFormat_exceptionThrown() {
        try {
            new Parser().parseCommand("deadline read book /by tomorrow");
            fail();
        } catch (Exception e) {
            assertEquals("OOPS!!! The deadline is in the wrong format.", e.getMessage());
        }
    }

}
