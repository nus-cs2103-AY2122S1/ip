package duke.command;

import duke.exception.DukeException;
import duke.task.Todo;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class TodoCommand extends Command {
    private Todo todo;

    public TodoCommand(String description) {
        todo = new Todo(description);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(todo);

        String response = "Got it. I've added this task:\n"
                + "       " + todo + "\n"
                + "     Now you have "
                + tasks.getSize() + (tasks.getSize() > 1 ? " tasks" : " task")
                + " in the list.";
        ui.showResponse(response);

        storage.save(tasks.getTaskList());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
