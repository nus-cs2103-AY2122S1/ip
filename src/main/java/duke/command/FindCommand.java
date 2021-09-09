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
                    assert (keywordTasks.getListSize() <= tasks.getListSize())
                            : "Task list found by find command should not be greater than original task list.";
                    keywordTasks.addTask(t);
                }
            }
        }

        return ui.showTasksFound(keywordTasks);
    }
}
