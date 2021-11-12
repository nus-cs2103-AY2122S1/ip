package duke;

import duke.exception.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import duke.command.AddCommand;
import duke.task.ToDo;

public class ParserTest {
    @Test
    public void testParse_toDo_success() throws IllegalCommandException, IllegalTaskException,
            IllegalFrequencyException, MissingSyntaxException, MissingIndexException, IllegalTimeException,
            IllegalDateException {
        String command1 = "todo read book";
        assertEquals(new AddCommand(new ToDo("read book")),
                duke.Parser.parse(command1));
    }
}
