package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void parse_incompleteStringInput_DukeExceptionThrown() {
        try {
            Parser.parse("done");
            fail();
        } catch (DukeException e) {
            assertEquals("Insufficient input received! Please indicate the task number of the completed task.",
                    e.getMessage());
        }
    }

    @Test
    public void parse_unknownCommand_success() {
        try {
            assertEquals(new Command.UnknowCommand(), Parser.parse("blah"));
        } catch (DukeException e) {
            fail();
        }

    }
}
