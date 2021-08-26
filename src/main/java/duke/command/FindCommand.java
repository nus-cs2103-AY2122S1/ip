package duke.command;

import duke.Storage;
import duke.Ui;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.util.ArrayList;

public class FindCommand extends Command {
    private String command;

    public FindCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (int i = 0; i < tasks.getNumberOfTasks(); i++) {
            if(tasks.getTask(i).getDescription().contains(command)) {
                foundTasks.add(tasks.getTask(i));
            }
        }
        ui.respondToFind(foundTasks);
    }

    @Override
    public Boolean isExit() {
        return false;
    }
}
