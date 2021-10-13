package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import duke.util.Parser;
import org.junit.jupiter.api.Test;

import duke.exceptions.DukeException;

public class ParserTest {
    @Test
    void parseCommand_parser_test() {
        try {
            Parser.parse("find");
        } catch (DukeException e) {
            assertEquals("Please specify keyword for find command. :)", e.getMessage());
        }
    }
}
