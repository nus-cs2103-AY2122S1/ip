package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command that mark certain task as done.
 */
public class MarkAsDoneCommand extends Command {
    private int doneListIndex; //index in the list

    public MarkAsDoneCommand(int doneListIndex) {
        this.doneListIndex = doneListIndex;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.get(doneListIndex).markTaskAsDone();
        storage.convertTaskListToFile(tasks);
        return ui.markAsDone(tasks.get(doneListIndex));
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof MarkAsDoneCommand) {
            @SuppressWarnings("have checked obj is MarkAsDoneCommand, can safely parse")
            MarkAsDoneCommand temp = (MarkAsDoneCommand) obj;
            return temp.doneListIndex == this.doneListIndex;
        } else {
            return false;
        }
    }
}
