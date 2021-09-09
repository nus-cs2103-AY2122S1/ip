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
            matchKeywordToTask(tasks, keywordTasks, keyword);
        }
        return ui.showTasksFound(keywordTasks);
    }

    private void matchKeywordToTask(TaskList tasks, TaskList keywordTasks, String keyword) {
        for (Task t : tasks.getTaskList()) {
            boolean isNotFound = !keywordTasks.getTaskList().contains(t);
            boolean isMatch = t.getDescription().toLowerCase().contains(keyword.toLowerCase());
            if (isNotFound && isMatch) {
                assert (keywordTasks.getListSize() <= tasks.getListSize())
                        : "Task list found by find command should not be greater than original task list.";
                keywordTasks.addTask(t);
            }
        }
    }
}
