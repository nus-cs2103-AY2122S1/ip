package duke;

import duke.command.AddCommand;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import duke.task.ToDo;
import duke.exception.IllegalCommandException;
import duke.exception.IllegalTaskException;

public class ParserTest {
    @Test
    public void testParse_toDo_success() throws IllegalCommandException, IllegalTaskException {
        String command1 = "todo read book";
        assertEquals(new AddCommand(new ToDo("read book")),
                duke.Parser.parse(command1));
    }
}
