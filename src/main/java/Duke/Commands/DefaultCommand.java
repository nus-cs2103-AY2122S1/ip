package Duke.Commands;

import Duke.DukeException;

public class DefaultCommand extends Command {
    public DefaultCommand(String command) {
        super(command);
        throw new DukeException("", DukeException.TYPE.SYNTAX_ERROR);
    }

    @Override
    public void execute() {

    }
}
