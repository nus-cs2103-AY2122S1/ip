package util;

import duke.exception.EmptyDescriptionException;
import duke.exception.IncompleteDescriptionException;
import duke.util.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {

    @Test
    public void emptyCommandTest() {
        assertThrows(EmptyDescriptionException.class, () -> Parser.parse(""));
    }

    @Test
    public void incompleteCommandTest() {
        assertThrows(EmptyDescriptionException.class, () -> Parser.parse("todo "));
    }

    @Test
    public void allSpaceCommandTest() {
        assertThrows(EmptyDescriptionException.class, () -> Parser.parse("      "));
    }

}
