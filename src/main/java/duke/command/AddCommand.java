package duke.command;

import duke.task.TaskList;

import duke.util.Ui;
import duke.util.Storage;

import java.io.IOException;

public class AddCommand extends Command {
    public AddCommand(String input) {
        super(input);
    }

    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        TaskList newTasks = tasks.addTask(input, ui);
        storage.saveTasksToFile(newTasks);
        return newTasks;
    }
}
