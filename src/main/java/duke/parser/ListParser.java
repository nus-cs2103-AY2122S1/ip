package duke.parser;

import java.util.Map;

import duke.command.ListCommand;
import duke.exception.DukeException;

public class ListParser extends CommandParser<ListCommand> {

    public ListParser() {
        super("list");
    }

    @Override
    protected ListCommand convertToCommand(Map<String, String> argumentMap) throws DukeException {
        return new ListCommand();
    }
}
