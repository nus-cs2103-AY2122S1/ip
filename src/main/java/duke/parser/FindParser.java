package duke.parser;

import java.util.Map;

import duke.command.FindCommand;
import duke.exception.DukeException;

public class FindParser extends CommandParser<FindCommand> {

    public FindParser() {
        super("find", "/query");
    }

    @Override
    protected FindCommand convertToCommand(Map<String, String> argumentMap) throws DukeException {
        if (argumentMap.containsKey("/query")) {
            return new FindCommand(argumentMap.get("/query"));
        } else {
            throw new DukeException("Sorry boss, find must have /query argument");
        }
    }
}
