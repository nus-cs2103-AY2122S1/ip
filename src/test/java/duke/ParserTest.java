package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.command.DeleteCommand;
import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    void parse() {
        try {
            assertEquals(new DeleteCommand(1), new Parser(" ").parse("delete 2"));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
