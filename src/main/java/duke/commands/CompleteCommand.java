package duke.commands;

import duke.exceptions.UserInputError;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.util.Storage;
import duke.util.Ui;

public class CompleteCommand extends Command {
    private final int index;

    public CompleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui) throws UserInputError {
        Task task = tasks.getTask(index);
        if (task.isDone()) {
            return ui.formatOutput("Great! But you have already completed this task!");
        } else {
            task.markDone();
            Storage.writeDatabase(tasks);
            return ui.formatOutput("Nice! I've marked this task as done: \n" + task);
        }
    }
}
