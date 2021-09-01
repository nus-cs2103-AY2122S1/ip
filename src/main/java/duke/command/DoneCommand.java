package duke.command;

import duke.DukeException;
import duke.Task;
import duke.TaskList;
import duke.Ui;

public class DoneCommand implements Command {
    private int idx;

    public DoneCommand(int idx) {
        super();
        this.idx = idx;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws DukeException {
        if (idx >= tasks.size()) {
            throw new DukeException("☹ OOPS!!! That task doesn't exist.");
        }
        Task t = tasks.get(idx);
        t.markAsDone();
        System.out.println(("Nice! I've marked this task as done: \n\t" + t));
    }

    public boolean isExit() {
        return false;
    }
}
