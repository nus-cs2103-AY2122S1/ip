package captain.parser;

import captain.DukeException;
import captain.DukeException.MissingDescriptionException;
import captain.command.AddCommand;
import captain.task.Todo;

public class AddCommandParser implements Parser<AddCommand> {
    @Override
    public AddCommand parse(String args) throws DukeException {
        if (args.isEmpty()) {
            throw new MissingDescriptionException();
        }
        return new AddCommand(new Todo(args));
    }
}
