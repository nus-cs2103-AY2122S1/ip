package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;
import java.util.List;

public class FindCommand extends Command {
    private String text;

    public FindCommand(String text) {
        this.text = text;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks.getTasks()) {
            if (task.toString().contains(text)) {
                matchingTasks.add(task);
            }
        }
        TaskList mTasks = new TaskList(matchingTasks);
        ui.showMatchingTasks(mTasks);
    }

    public boolean isExit() {
        return false;
    }
}
