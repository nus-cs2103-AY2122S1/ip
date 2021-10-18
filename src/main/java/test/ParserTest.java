package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.Parser;
import duke.exceptions.DukeException;


class ParserTest {

    @Test
    void checkCommandTag_unknownTag_excpetionThrown() {
        try {
            assertEquals("", new Parser().checkCommandTag("schedule meeting"));
            fail();
        } catch (DukeException e) {
            assertEquals("No such tag! Please input the correct tag!", e.getMessage());
        }
    }

    @Test
    void checkCommandTag_nonIntegerIdTaskCompleted_excpetionThrown() {
        try {
            assertEquals("", new Parser().checkCommandTag("done x"));
            fail();
        } catch (DukeException e) {
            assertEquals("Wrong format! Please enter a valid id.", e.getMessage());
        }
    }

    @Test
    void checkCommandTag_wrongDateFormat_excpetionThrown() {
        try {
            assertEquals("", new Parser().checkCommandTag("event conference /at 01/10/2021"));
            fail();
        } catch (DukeException e) {
            assertEquals("Wrong format! Please follow the format: type description /at yyyy-mm-dd.", e.getMessage());
        }
    }

    @Test
    void checkCommandTag_wrongDeadlineFormat_excpetionThrown() {
        try {
            assertEquals("", new Parser().checkCommandTag("deadline conference by 2021-09-18"));
            fail();
        } catch (DukeException e) {
            assertEquals("Wrong format! Please follow the format: type description /xx yyyy-mm-dd\n\t "
                    + "Use /by for deadline, /at for event.", e.getMessage());
        }
    }
}
