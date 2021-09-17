package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command that mark certain task as done.
 */
public class MarkAsDoneCommand extends Command {
    private int taskIndex; //index in the list

    public MarkAsDoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IndexOutOfBoundsException {
        if (tasks.size() - 1 < taskIndex) {
            throw new IndexOutOfBoundsException("This task doesn't exit, please enter a valid task index.");
        }
        tasks.get(taskIndex).markTaskAsDone();
        storage.convertTaskListToFile(tasks);
        return ui.markAsDone(tasks.get(taskIndex));
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
            return temp.taskIndex == this.taskIndex;
        } else {
            return false;
        }
    }
}
