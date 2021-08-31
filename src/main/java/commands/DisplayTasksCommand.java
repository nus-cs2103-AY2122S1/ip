package commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DisplayTasksCommand implements Command {

    public String execute(Ui ui, TaskList taskList, Storage storage) {
        if (taskList.numberOfTasks() > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("Here are your tasks! \n");
            for (int i = 0; i < taskList.numberOfTasks(); i++) {
                sb.append((i + 1) + ". " + taskList.getTask(i) + "\n");
            }
            return sb.toString();
        } else {
            return "You have no tasks! Nice.";
        }
    }

    public boolean isQuit() {
        return false;
    }
}
