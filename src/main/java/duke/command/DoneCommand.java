package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DoneCommand extends Command {

    private int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.markTaskAsDone(this.index);
        storage.writeToFile(tasks);
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof DoneCommand) && (this.index == ((DoneCommand) obj).index);
    }
}
