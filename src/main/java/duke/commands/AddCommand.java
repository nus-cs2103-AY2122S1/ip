package duke.commands;

import java.io.IOException;

import duke.storage.Storage;
import duke.storage.TaskList;
import duke.tasks.Task;

public class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) {
        String inputToStorage;
        try {
            taskList.addTask(this.task);

            inputToStorage = this.task.getSymbol() + " | 0 | " + this.task.getDescription();
            if (!this.task.getSymbol().equals("T")) {
                inputToStorage += " | " + this.task.getDateTime();
            }

            storage.appendToData(inputToStorage);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public boolean getIsExit() {
        return false;
    };
}
