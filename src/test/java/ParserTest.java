import duke.Duke;
import duke.exception.DukeException;
import duke.parser.Parser;

import duke.task.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void rubbishTest(){
        try {
            Parser.parse("rubbish", new TaskList());
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! I'm sorry, but I don't know what that means :-(", e.getMessage());
        }
    }
}