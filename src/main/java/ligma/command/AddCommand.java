package ligma.command;

import ligma.Storage;
import ligma.TaskList;
import ligma.Ui;
import ligma.task.Deadline;
import ligma.task.Event;
import ligma.task.Task;
import ligma.task.Todo;

import java.io.IOException;

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
        default:
            return null;
        }
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        try {
            tasks.addTask(task);
            storage.saveTask(task);
            Ui.printSuccessMessage("added:\n " + task
                    + String.format("\n You now have %d task(s).", tasks.getTaskAmt()));
        } catch (IOException e) {
            Ui.printErrorMessage("Failed to save task to storage: \n" + task);
        }
    }

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
