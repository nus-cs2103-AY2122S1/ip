package duke.parser;

import java.util.Map;

import duke.command.ByeCommand;
import duke.exception.DukeException;

public class ByeParser extends CommandParser<ByeCommand> {

    public ByeParser() {
        super("bye");
    }

    @Override
    protected ByeCommand convertToCommand(Map<String, String> argumentMap) throws DukeException {
        return new ByeCommand();
    }
}
