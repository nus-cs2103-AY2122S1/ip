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
        TaskList filterTaskList = filterTaskListWithKeywords(tasks);
        return ui.showTasksFound(filterTaskList);
    }

    /**
     * Filters the task list according to the keyword provided.
     * @param tasks The list of tasks.
     * @return A TaskList containing only the tasks that contains the keyword.
     */
    public TaskList filterTaskListWithKeywords(TaskList tasks) {
        List<Task> taskList = tasks
                .getTaskList()
                .stream()
                .filter((task -> matchesKeywords(task, keywords)))
                .collect(Collectors.toList());
        ArrayList<Task> keywordTasks = new ArrayList<>(taskList);
        return new TaskList(keywordTasks);
    }

    /**
     * Matches the task with the list of keywords.
     * @param task The identified task to match.
     * @param keywords The list of keywords input by user.
     * @return True if the task contains at least one of the keywords. Otherwise, return false.
     */
    public boolean matchesKeywords(Task task, String[] keywords) {
        for (String keyword : keywords) {
            if (containsKeyword(task, keyword)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the task contains a keyword. This check is case-insensitive.
     * @param task The identified task to check.
     * @param keyword A keyword from the list of keywords input by user.
     * @return True if the task contains the keyword. Otherwise, return false;
     */
    public boolean containsKeyword(Task task, String keyword) {
        String taskDescription = task.getDescription().toLowerCase();
        keyword = keyword.toLowerCase();
        return taskDescription.contains(keyword);
    }

}
