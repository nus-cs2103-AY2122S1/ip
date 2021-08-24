package duke.misc;

import duke.exception.DukeException;
import duke.task.Event;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


class ParserTest {
    @Test
    public void testMakeTask() {
        try {
            assertEquals(new Event("Gala", "2022-01-26"), new Parser().
                    makeTask("event Gala2022-01-26/"));
            fail();
        } catch (DukeException e) {
            assertEquals("duke.exception.InvalidFormatException: Sorry >.< but this format is invalid!\n" +
                    "Please follow this format: [<Type> <Description> / <Date/Time>].", e.toString());
        }
    }
}