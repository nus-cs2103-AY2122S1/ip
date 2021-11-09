package ligma.command;

import java.io.IOException;

import ligma.Storage;
import ligma.TaskList;
import ligma.task.*;
import ligma.ui.Ui;

/**
 * This class represents a command to add a task.
 */
public class AddCommand implements Command {
    private Task task;

    public AddCommand(Task.TaskType type, String desc) {
        this.task = createTask(type, desc);
    }

    private Task createTask(Task.TaskType type, String desc) {
        switch (type) {
        case TODO:
            return new Todo(desc);
        case EVENT:
            return Event.createEvent(desc);
        case DEADLINE:
            return Deadline.createDeadline(desc);
        case RECURRING:
            return Recurring.createRecurring(desc);
        default:
            return null;
        }
    }

    /**
     * Adds task to tasklist and storage as well as reflect execution status on UI.
     *
     * @param tasks     all tasks belonging to current Ligma program
     * @param storage   storage of current Ligma program
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        try {
            tasks.addTask(task);
            storage.saveTaskList(tasks);
            return Ui.getSuccessMessage("added:\n" + task.toString());
        } catch (IOException e) {
            return "Failed to save task to storage: \n" + task;
        }
    }

    /**
     * Returns true if command is an exit command.
     *
     * @return true if command is an exit command, false otherwise
     */
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AddCommand) {
            return this.task.equals(((AddCommand) obj).task);
        }
        return false;
    }
}
