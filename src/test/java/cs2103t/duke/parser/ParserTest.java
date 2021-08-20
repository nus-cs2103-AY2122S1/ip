package cs2103t.duke.parser;

import cs2103t.duke.ui.Ui;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ParserTest {

    @Test
    public void parseCommand_wrongDeadlineFormat_exceptionThrown() {
        String space = Ui.SPACE;
        try {
            new Parser().parseCommand("deadline read book /by tomorrow");
            fail();
        } catch (Exception e) {
            assertEquals(space + "☹ OOPS!!! The deadline is in the wrong format.", e.getMessage());
        }
    }

}