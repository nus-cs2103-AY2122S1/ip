package duke;

import duke.exception.DukeException;

public class ByeCommand extends Command {
    public ByeCommand(TaskList tasks) {
        super(tasks);
    }

    @Override
    public String run() throws DukeException {
        return "Babai! See you again soon! XD";
    }

    @Override
    public String undo() {
        return "Nothing to undo :/";
    }
}
