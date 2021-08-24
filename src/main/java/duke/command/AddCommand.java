package duke.command;

import duke.task.Deadline;
import duke.task.Event;
import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.Ui;

public class AddCommand extends Command {
    Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(this.task);

        if (task instanceof Todo) {
            Todo todo = (Todo) this.task;
            storage.addTodo(todo);
            ui.addTodo(taskList, todo);
        }

        if (task instanceof Deadline) {
            Deadline deadline = (Deadline) this.task;
            storage.addDeadline(deadline);
            ui.addDeadline(taskList, deadline);
        }

        if (task instanceof Event) {
            Event event = (Event) this.task;
            storage.addEvent(event);
            ui.addEvent(taskList, event);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
