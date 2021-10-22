package captain.parser;

import captain.DukeException;
import captain.command.DeleteCommand;

public class DeleteCommandParser implements Parser<DeleteCommand> {

    @Override
    public DeleteCommand parse(String args) throws DukeException {
        try {
            int index = Integer.parseInt(args);
            return new DeleteCommand(index);
        } catch (NumberFormatException e) {
            throw new DukeException.InvalidTaskIndexException();
        }
    }
}
