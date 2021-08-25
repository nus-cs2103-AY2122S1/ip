package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

public class TodoCommand extends AddCommand {
    /**
     * Constructs a TodoCommand with the specified description.
     *
     * @param description
     */
    public TodoCommand(String description) {
        super(description);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = new Todo(description);
        tasks.add(task, storage);
        System.out.println("Got it. I've added this task:\n  " +
                task +
                "\nNow you have " + tasks.toArray().length + " task(s) in the list.");
    }
}
