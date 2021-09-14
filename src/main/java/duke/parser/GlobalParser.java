package duke.parser;

import java.util.Map;

import duke.command.Command;
import duke.exception.DukeException;

public class GlobalParser extends CommandParser<Command> {
    public GlobalParser() {
        super(null);
        registerParsers(
                new TodoParser(), new EventParser(), new DeadlineParser(),
                new DoneParser(), new DeleteParser(), new UpdateParser(),
                new FindParser(), new ListParser(), new ByeParser()
        );
    }

    @Override
    protected Command convertToCommand(Map<String, String> argumentMap) throws DukeException {
        return null;
    }
}
