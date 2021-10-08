package duke.parser;

import java.util.Map;

import duke.command.AddCommand;
import duke.exception.DukeException;
import duke.task.ToDo;

public class TodoParser extends CommandParser<AddCommand> {

    public TodoParser() {
        super("todo", "/desc");
    }

    @Override
    protected AddCommand convertToCommand(Map<String, String> argumentMap) throws DukeException {
        if (argumentMap.containsKey("/desc")) {
            return new AddCommand(new ToDo(argumentMap.get("/desc"), false));
        } else {
            throw new DukeException("Sorry Boss, todo must have /desc argument");
        }
    }
}
