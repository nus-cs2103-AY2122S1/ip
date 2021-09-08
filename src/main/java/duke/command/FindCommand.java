package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class FindCommand extends Command {
    private String[] keywords;

    public FindCommand(String ... keywords) {
        this.keywords = keywords;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList keywordTasks = new TaskList();
        for (String keyword : keywords) {
            for (Task t : tasks.getTaskList()) {
                if (!keywordTasks.getTaskList().contains(t)
                        && t.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                    keywordTasks.addTask(t);
                }
            }
        }
        return ui.showTaskList(keywordTasks);
    }
}
