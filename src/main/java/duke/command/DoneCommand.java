package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DoneCommand extends Command {
    private final int indexToMarkAsDone;

    public DoneCommand(int indexToMarkAsDone) {
        this.indexToMarkAsDone = indexToMarkAsDone;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.markAsDone(this.indexToMarkAsDone);
        String message = "Nice! I've marked this task as done:\n" + "  "
                + tasks.taskToString(this.indexToMarkAsDone);
        ui.print(message);
    }
}
