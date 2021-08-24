package duke.commands;

import java.io.IOException;

import duke.storage.Storage;
import duke.storage.TaskList;
import duke.tasks.Task;

public class AddCommand extends Command {
    public void execute(TaskList taskList, Storage storage, Task task) {
        String inputToStorage;
        try {
            taskList.addTask(task);

            inputToStorage = task.getSymbol() + " | 0 | " + task.getDescription();
            if (!task.getSymbol().equals("T")) {
                inputToStorage += " | " + task.getDateTime();
            }

            storage.appendToData(inputToStorage);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void execute(TaskList taskList, Storage storage) {

    }
}
