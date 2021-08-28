package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword.toLowerCase();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList keywordTasks = new TaskList();
        for (Task t : tasks.getTaskList()) {
            String description = t.getDescription().toLowerCase();
            if (description.contains(keyword)) {
                keywordTasks.addTask(t);
            }
        }
        ui.showTaskList(keywordTasks);
    }
}
