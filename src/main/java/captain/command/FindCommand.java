package captain.command;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import captain.Storage;
import captain.Ui;
import captain.task.Task;
import captain.task.TaskList;

public class FindCommand extends Command {
    private String[] keywords;

    public FindCommand(String ... keywords) {
        this.keywords = keywords;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList filterTaskList = keywords.length == 1
                ? filterWithSingleKeyword(tasks, keywords[0])
                : filterWithMultipleKeywords(tasks);
        return ui.showTasksFound(filterTaskList);
    }

    private TaskList filterWithMultipleKeywords(TaskList tasks) {
        TaskList filteredTasks = new TaskList();
        for (String keyword : keywords) {
            filterTasksWithKeyword(tasks, filteredTasks, keyword);
        }
        return filteredTasks;
    }

    /**
     * Filters the task list according to the keyword provided.
     * @param tasks The list of tasks.
     * @param keyword The keyword input by the user.
     * @return A TaskList containing only the tasks that contains the keyword.
     */
    public TaskList filterWithSingleKeyword(TaskList tasks, String keyword) {
        List<Task> taskList = tasks
                .getTaskList()
                .stream()
                .filter((task -> containsKeyword(task, keyword)))
                .collect(Collectors.toList());
        ArrayList<Task> keywordTasks = new ArrayList<>(taskList);
        return new TaskList(keywordTasks);
    }

    public boolean containsKeyword(Task t, String keyword) {
        return t.getDescription().toLowerCase().contains(keyword.toLowerCase());
    }

    private void filterTasksWithKeyword(TaskList tasks, TaskList keywordTasks, String keyword) {
        for (Task t : tasks.getTaskList()) {
            boolean notYetFound = !tasks.containsTask(t);
            boolean isMatch = containsKeyword(t, keyword);
            if (notYetFound && isMatch) {
                assert (keywordTasks.getListSize() <= tasks.getListSize())
                        : "Filtered list should not be greater than original list.";
                keywordTasks.addTask(t);
            }
        }
    }
}
