package duke;

import duke.command.Command;
import duke.command.CommandsTypes;
import duke.task.Task;
import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void testAdd() {
        try {
            Parser parser = new Parser();
            Task newTask = Task.makeTask("todo", "get a life");
            Command addCommand = Command.makeCommand(CommandsTypes.Add, )
        } catch (DukeException e) {
            
        }

    }
}
