package duke.parser;

import java.util.Map;

import duke.command.DoneCommand;
import duke.exception.DukeException;

public class DoneParser extends CommandParser<DoneCommand> {

    public DoneParser() {
        super("done", "/sn");
    }

    @Override
    protected DoneCommand convertToCommand(Map<String, String> argumentMap) throws DukeException {
        try {
            if (argumentMap.containsKey("/sn")) {
                return new DoneCommand(Integer.parseInt(argumentMap.get("/sn")));
            } else {
                throw new DukeException("Sorry Boss, done must have /sn argument");
            }
        } catch (NumberFormatException e) {
            throw new DukeException("Sorry Boss, please pass a valid number for /sn argument");
        }
    }
}
