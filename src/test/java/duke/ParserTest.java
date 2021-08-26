package duke;

import duke.command.AddTaskCommand;
import duke.command.Command;
import duke.task.Task;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void testParserOutput(){
        Command c = Parser.parse("todo test");
        assertEquals(c instanceof AddTaskCommand, true);
    }
}
