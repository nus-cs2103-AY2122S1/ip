package duke;

import duke.exception.DukeException;

public class FindTaskCommand extends UndoableCommand {

    public FindTaskCommand(TaskList tasks, String input) {
        super(tasks, input);
    }

    @Override
    public String run() throws DukeException {
        return tasks.findTask(input);
    }

    @Override
    public String undo() {
        return "Nothing to undo :/";
    }
}
