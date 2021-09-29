package captain.parser;

import captain.DukeException;
import captain.command.DoneCommand;


public class DoneCommandParser implements Parser<DoneCommand> {

    @Override
    public DoneCommand parse(String args) throws DukeException {
        try {
            int index = Integer.parseInt(args);
            return new DoneCommand(index);
        } catch (NumberFormatException e) {
            throw new DukeException.InvalidTaskIndexException();
        }
    }
}
