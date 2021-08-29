package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.Ui;

public class deleteCommand extends Command {
    private String command;
    
    public deleteCommand(String command) {
        super(command);
        this.command = command;
    }

    public String toString() {
        return "This is a delete command";
    }

    public void execute(TaskList taskList, Storage storage) {
        int value = Integer.parseInt(command.replaceAll("[^0-9]", ""));
        Task task = taskList.getTask(value-1);
        taskList.removeTask(value-1);
        Ui.deleteResponse(task);
        storage.writeToFile("./duke.txt", taskList);
    }
}