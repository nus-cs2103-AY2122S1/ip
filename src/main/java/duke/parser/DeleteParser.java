package duke.parser;

import java.util.Map;

import duke.command.DeleteCommand;
import duke.exception.DukeException;

public class DeleteParser extends CommandParser<DeleteCommand> {

    public DeleteParser() {
        super("delete", "/sn");
    }

    @Override
    protected DeleteCommand convertToCommand(Map<String, String> argumentMap) throws DukeException {
        try {
            if (argumentMap.containsKey("/sn")) {
                return new DeleteCommand(Integer.parseInt(argumentMap.get("/sn")));
            } else {
                throw new DukeException("Sorry Boss, delete must have /sn argument");
            }
        } catch (NumberFormatException e) {
            throw new DukeException("Sorry Boss, please pass a valid number for /sn argument");
        }
    }
}
