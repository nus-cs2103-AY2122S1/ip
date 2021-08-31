package duke.command;

import duke.Storage;
import duke.Ui;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.util.ArrayList;

public class DeleteCommand extends Command {
    String taskNumber;
    public DeleteCommand(String command) {
        this.taskNumber = command;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Integer number = Integer.valueOf(this.taskNumber);
        ArrayList<Task> originalTaskList = tasks.getTasks();
        Task task = tasks.getTask(number - 1);
        tasks.getTasks().remove(number - 1);
        storage.rewriteFile(tasks.getTasks());
        return ui.respondToDelete(tasks.getTasks(), task);
    }

    @Override
    public Boolean isExit() {
        return false;
    }
}
